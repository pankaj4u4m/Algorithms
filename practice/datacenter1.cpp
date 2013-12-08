/*
    An elegant, ultra fast solution to the Quora Datacenter Cooling Challenge.
 
    In a valid layout of the duct through the rooms in the datacenter:
        1) Every room must have exactly 2 connections/edges.
                Rooms that we own "0" must have an incoming and an outgoing edge.
                Start room "2" must have an outgoing edge and an imaginary incoming edge.
                End room "3" must have an incoming edge and an imaginary outgoing edge.
                Rooms that we don't own "1" are assumed to already have 2 edges.
        2) There are no cycles. The following layout is not valid.
                2  0--0
                |  |  |
                0  0--0
                |
                0--0--3
            Every room above has two edges, but the four rooms in top-right form a cycle.
            They are not part of the path from room "2" to "3".
 
    "A valid layout of the duct is one in which each room has two edges and there are no cycles."
 
    Edges can be thought to be direction-less for layout.
    There are 4 possible edges that a room can have:
               UP
               |
      LEFT --- Rm --- RIGHT
               |
              DOWN
 
    The LEFT edge of a room is the RIGHT edge of the room to it's left.
    The UP edge of a room is the DOWN edge of the room above it.
 
    If we process the rooms left to right, top to bottom, starting with the room at
    the top-left corner, then we only need to consider the RIGHT and DOWN edges of a room.
    The top edge of a room would've been handled when the room immediately above it, in the
    preceeding row, was processed. Similarly the left edge of the room would already have been
    handled when the room immediately to the left, in the current row, was processed.
 
    An unprocessed room would look like following:
 
        |                |
     -- Rm     (Or)     Rm      (Or)   -- Rm         (Or)      Rm
 
     (Case A)         (Case B)          (Case C)              (Case D)
 
     In Case A, the room already has 2 connections and we can skip to the next room.
     In Case B and Case C, the room has just one connection and we need to add one more if the room is "0".
     In Case D, the room has no connections and we must add 2 connections (just 1 if it is "2" or "3").
 
     If an unprocessed room has no connections (Case D), then we must connect it both to the right and down:
                Rm ---
                 |
 
    If and unprocessed room has just one connection, then we need to connect it first to the right, continue solving,
    then remove the connection and reconnect down to the room below and then solve.
 
    Preventing Cycles:
    A cycle is only formed when a room is connected to the room on its right and there's already a path from the room to
    the room on its right:
 
            0 -- 0 -- 0 -- 0    0 -- 2
            |              |    |
            0    1    0 - -0    0    1
            |         |         |
            0 -- 0    0    0    0    1
            R0   R1   R2   R3   R4
 
    In the above example, there's already a path from R1 to R2, so connecting R1 right to R2 would lead to a cycle.
 
    Therefore to prevent cycles, we need to keep track of the end point (ending room) of the path from a room.
 
            0 -- 0 -- 0 -- 0    0 -- 2
            |              |    |
            0    1    0 -- 0    0    1
            |         |         |
            0    0    0    0    0    1
            R0   R1   R2   R3   R4
 
    In the above example, the path-end-point of R0 is R2 and vice-versa.
    The end-point of "2" is R4 and vice-versa.
    When we make a new connection, we update the end-points:
 
            0 -- 0 -- 0 -- 0    0 -- 2
            |              |    |
            0    1    0 -- 0    0    1
            |         |         |
            0 -- 0    0    0    0    1
            R0   R1   R2   R3   R4
 
    After connecting R0 right to R1, the path-end-point of R1 is R2 and that of R2 is now R1.
    We don't really care about of end-point of R0 at this point.
 
    This approach also automatically merges two paths when a connection is made.
 
        2    0 -- 0 -- 0
        |    |         |
        0    0    0    0
        R0   R1   R2   R3
 
    In the above example, the end-point of R0 is "2" and vice-versa.
    The end-point of R1 is R3 and vice-versa.
    When we connect R0 to R1 (this does not introduce a cycle is end-point of R1 is not R0),
    we get the end-points of R0 and R1 and make them "end-point" each-other.
 
        2    0 -- 0 -- 0           e1 <- endpoint(R0)
        |    |         |           e2 <- endpoint(R1)
        0 -- 0    0    0           endpoint(e1) <- e2
        R0   R1   R2   R3          endpoint(e2) <- e1
 
    After connecting R0 to R1 and performing updates as described above, endpoint of "2" is R3 and vice-versa.
    When a connection is removed, we restore the end-points of the rooms.
 
    Memoization
 
    "Rooms in a row that is just about to be processed have paths from "2", "3" or other rooms in that row."
 
    Row 0 :     2    0 -- 0 -- 0 -- 0                        2 -- 0    0 -- 0 -- 0
                |    |              |                             |    |         |
    Row 1:      0    0 -- 0    3    0                        0 -- 0    0    3    0
                |         |    |    |                        |         |    |    |
    Row 2:      0    0    0    0    0                        0    0    0    0    0
                R0   R1   R2   R3  R4                        R0   R1   R2   R3  R4
 
                     (layout A)                                     (layout B)
 
    In the above example, we've finished processing Row 1 and are about to process Row 2.
    R0 has a path from "2", R3 has a path to "3".
    R1 (has an imaginary path to itself), R2 and R4 all have paths to other rooms in the same row.
 
    The algorithm relies *only* on the path-end of rooms and the number of connections.
    This means that processing Row 2 in layout A and in layout B would produce the same results.
 
    The path-ends of rooms in an unprocessed row uniquely identifies it in the context of a partial solution.
    It doesn't matter how the paths themselves are laid out, only the path-ends matter.
    So the list of path-ends of (the next) unprocessed row, can be used as key in a cache used for memoization.
 
    So the result of processing Row 2 in layout A can be memoized and later reused while processing
    Row 2 in layout B.
 
    Efficient Memoization
 
    A backtracking solution with memoization would (if implemented for maximum speed) put all the valid path-end combinations
    of all the rows in the cache. The back-tracking solution keeps recursing from Row 0 to Row R, then backup to Row 0 and again
    to Row R (where R is the number of rows). This gets memory intensive with a large grid like 14x14.
 
    To be memory efficient, we can process strictly row-wise. Rather than processing the next (unprocessed) row immediately after a
    row has been processed, we can put the unprocessed row in a work list for later processing. We go back to solving the current row,
    generating all possible valid path-end combinations for the next row. Once we are completely done with the current row, we
    move on to the next row. The next row becomes the current row.
    We take each path-end combination from the worklist, apply it back on to the row and process the row, generating valid path-end combinations
    for the new next row.
 
*/
 
#if defined(__GCC__)
 #define FORCE_INLINE __attribute__((always_inline))
#elif defined(_MSC_VER)
 #define FORCE_INLINE __forceinline
 #define _SECURE_SCL 0
#else
 #define FORCE_INLINE inline
#endif
 
#include <vector>
#include <iostream>
#include <map>
#include <cassert>
 
struct Room {
    Room* path_end;
    int   connections;
};
 
// A connection between a room and it's neighbor (either right or down)
struct Connection {
    Room* room;
    Room* neighbor;
 
    Room* room_end;
    Room* neighbor_end;
    bool  connected;
 
    FORCE_INLINE Connection(Room* r, Room* n)
    {
        room = r;
        neighbor = n;
        connected = false;
 
        // Make sure rooms are not fully connected..
        if (room->connections != 2 && neighbor->connections != 2) {
            // Check for cycles. This is a key semantic and performance constraint.
            if (room != neighbor->path_end) {
                room_end       = room->path_end;
                neighbor_end   = neighbor->path_end;
 
                /* sanity checks*/
                assert(room_end->path_end == room);
                assert(neighbor_end->path_end == neighbor);
                connected = true;
 
                // Update ends of the merged path to point to each other
                room_end->path_end     = neighbor_end;
                neighbor_end->path_end = room_end;
 
                room->connections++;
                neighbor->connections++;
            }
        }
    }
 
    FORCE_INLINE ~Connection()
    {
        if (connected) {
            // Restore path end-points.
            room_end->path_end     = room;
            neighbor_end->path_end = neighbor;
 
            room->connections--;
            neighbor->connections--;
        }
    }
 
    FORCE_INLINE operator bool() const { return connected; }
};
 
// Enable vector<Room> to be used as key in std::map
FORCE_INLINE bool operator< (const Room& r1, const Room& r2) { return r1.path_end < r2.path_end; }
 
typedef std::map<std::vector<Room>, unsigned long long> Cache;
 
void solve(std::vector< std::vector<Room> >& datacenter, Cache& cache, unsigned long long cur_count, int row, int col, unsigned long long* solutions)
{
    const int R = datacenter.size()    - 1;  // Account for extra row
    const int C = datacenter[0].size() - 1;  // and coloumn
 
    if (col == C) {                          // Current row's been processed.
        col = 0;
        if (row+1 == R)                      // It was the final row; a solution!
            *solutions += cur_count;
        else                                 // Update the number of times we've seen the next row in the cache/worklist.
            cache[datacenter[row+1]] += cur_count;
        return;
    }
 
    Room* room       = &datacenter[row][col];
    Room* room_right = &datacenter[row][col+1];
    Room* room_down  = &datacenter[row+1][col];
 
    if (room->connections == 2)
         return solve(datacenter, cache, cur_count, row, col+1, solutions);
 
    if (room->connections == 0) {
        // Room *must* be connected on both right and down.
        if (const Connection& connect_right = Connection(room, room_right))
            if(const Connection& connect_down = Connection(room, room_down))
                solve(datacenter, cache, cur_count, row, col+1, solutions);
        return;
    }
 
    // room->connections == 1.
    // Connect first to the right and explore.
    if (const Connection& connect_right = Connection(room, room_right))
        solve(datacenter, cache, cur_count, row, col+1, solutions);
 
    // Then connect down and explore.
    if (const Connection& connect_down = Connection(room, room_down))
        solve(datacenter, cache, cur_count, row, col+1, solutions);
}
 
unsigned long long solve(std::vector< std::vector<Room> >& datacenter)
{
    const int R = datacenter.size()    - 1;  // Account for extra row
    const int C = datacenter[0].size() - 1;  // and coloumn
 
    unsigned long long solutions = 0;
 
    Cache cur_row_cache;
    cur_row_cache[datacenter[0]] = 1;        // Populate first row in cache/worklist
 
    for (int row=0; row < R; ++row) {
        Cache next_row_cache;               // All valid path-end combinations of the next row
 
        // Iterate through the work list. Don't clear it one by one. Clear it in one swoop later at end of scope after swap.
        for (Cache::const_iterator itr = cur_row_cache.begin(); itr != cur_row_cache.end(); ++itr) {
            datacenter[row]         = itr->first;     // Restore the current row from the worklist
            unsigned long long cur_count = itr->second;    // The number of times we've seen the row with the path-end combination
 
            // To continue solving, make the ends point to each-other
            for (int c=0; c < C; ++c) {
                Room* room          = &datacenter[row][c];
                Room* room_path_end = room->path_end;
 
                room_path_end->path_end = room;
            }
 
            // Process the row and populate the worklist for the next row
            solve(datacenter, next_row_cache, cur_count, row, 0, &solutions);
        }
 
        // The next row becomes the current row.
        std::swap(cur_row_cache, next_row_cache);
    }
 
    return solutions;
}
 
int main(int argc, char** argv)
{
    std::vector< std::vector<Room> > datacenter;
    int R = 0;
    int C = 0;
 
    std::cin >> C;
    std::cin >> R;
 
    datacenter.resize(R+1);
    for(int r=0; r < R+1; ++r) {
        datacenter[r].resize(C+1);
        for(int c=0; c < C+1; ++c) {
            Room* room = &datacenter[r][c];
            room->path_end = room;                    // This assignment simplifies room connection.
            int kind = 1;
            if (r < R && c < C)
                std::cin >> kind;
            switch (kind) {
            case 0: room->connections = 0; break;     // A room we own. It has not been connected.
            case 1: room->connections = 2; break;     // Unowned room. Assume it is fully connected.
            case 2: room->connections = 1; break;     // Start room. Assume there's a connection from an external source.
            case 3: room->connections = 1; break;     // End room. Assume there's a connection to an external destination.
            default: assert(false); break;
            }
        }
    }
 
    std::cout << solve(datacenter);
}

