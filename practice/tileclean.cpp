
//	Author:  pankaj kumar, pankaj4u4m@gmail.com
#include <cassert>//c headers in c++
#include <cctype>
#include <cfloat>
#include <cmath>
#include <cstdarg>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <climits>
#include <algorithm>//c++ headers
#include <bitset>
#include <complex>
#include <deque>
#include <functional>
#include <iostream>
#include <iomanip>
#include <iterator>
#include <limits>
#include <list>
#include <map>
#include <memory>
#include <new>
#include <numeric>
#include <queue>
#include <set>
#include <sstream>
#include <stack>
#include <string>
#include <utility>
#include <valarray>
#include <vector>
using namespace std;
/*START OF TEMPLATE:CODEGAMBLER*/
#define VAR(x,a) 	__typeof(a) x=(a)//::VAR(
#define FE(it,c)	for(VAR(it,(c).begin());it!=(c).end();++it)//::FE(
#define FOR(i,a,b)  	for(int i=(int)(a),_b=(int)(b) ; i < _b;++i)//::FOR(
#define FORR(i,a,b) 	for(int i=(a),_b=(b);i>=_b;--i)//::FORR(
#define	REP(i,n)    	FOR(i,0,n)//::REP(
#define ALL(c)		(c).begin(),(c).end()//::ALL(
#define SZ(c)		(int)(c).size()//::SZ(
#define PB      push_back//::PB
#define V(x)    vector< x >//::V(
#define VI      V(int)//::VI
#define VVI     V(VI)//::VII
#define VS      V(string)//::VS
#define PI      pair< int,int >//::PI
#define MP      make_pair//::MP
#define pi      3.1415926535897932384626433832795//::pi
#define INF 	2000000000//::INF

const double eps=1e-11;//::eps
typedef long long LL;//::LL
typedef unsigned long long ULL;//::ULL
typedef long double LD;//::LD

/* Only for Debugging */
#define out(__debug) cout << #__debug<< "=" <<__debug << endl;//::out(
#define outC(A) cout<<"{"; FE(it,A)cout << *it << " " ;cout<<"}"<<endl;//::outContainer
template<class T>inline void outA(T A[], int n) {cout<<"{"; REP (i, n)cout<<A[i]<<" "; cout<<"}"<<endl;}//:outArray

/**************** Main program *******************/
char matrix[25][25];
int dist[25][25][25][25];

int distance(PI &a, PI&p, int row, int col){
	if(dist[a.first][a.second][p.first][p.second] != -1){
		return dist[a.first][a.second][p.first][p.second];
	}
	queue<pair<int, PI > > q;
	q.push(MP(0, a));
	bool visited[row][col];
	memset(visited, 0, sizeof visited);

	while(!q.empty()){
		pair<int, PI >v = q.front();q.pop();
		PI top = v.second;
		if(visited[top.first][top.second]){
			continue;
		}
		visited[top.first][top.second] = true;
		dist[a.first][a.second][top.first][top.second] = v.first;

		if(top.first + 1 < row && !visited[top.first+1][top.second] && matrix[top.first+1][top.second] != 'x'){
			q.push(MP(v.first+1, MP(top.first+1, top.second)));
		}
		if(top.first - 1 >= 0 && !visited[top.first-1][top.second]&& matrix[top.first-1][top.second] != 'x'){
			q.push(MP(v.first+1, MP(top.first - 1, top.second)));
		}
		if(top.second + 1 < col && !visited[top.first][top.second+1]&& matrix[top.first][top.second+1] != 'x'){
			q.push(MP(v.first+1, MP(top.first, top.second + 1)));
		}
		if(top.second - 1 >= 0 && !visited[top.first][top.second-1] && matrix[top.first][top.second-1] != 'x'){
			q.push(MP(v.first+1, MP(top.first, top.second - 1)));
		}
	}
	return dist[a.first][a.second][p.first][p.second];
}

int solve(PI &pos, int row, int col, vector<PI > &dirty, int start, int sz){
	if(start >= sz){
		return 0;
	}
	int mn = INF;
	for(int i = start; i< sz; ++i){
		PI p = dirty[i];
		dirty[i] = dirty[start];
		int d = distance(pos, p, row, col);
		if(d == -1){
			return -1;
		}
		int s = solve(p, row, col, dirty, start+1, sz);
		if(s == -1){
			return -1;
		}
	  mn = min(mn, d + s);
		dirty[i] = p;
	}
	return mn;
}

int main ( int argc, char *argv[] ) {
	//freopen("tileclean.cppin","r",stdin);
	int row, col;scanf("%d%d", &col, &row);
	vector<pair<int, int> > dirty;
	PI start;
	for(int i = 0; i< row; ++i){
		scanf("%s", matrix[i]);
		for(int j = 0; j< col; ++j){
			if(matrix[i][j] == '*'){
				dirty.push_back(MP(i, j));
			} else if(matrix[i][j] == 'o'){
				start = MP(i, j);
			}
		}
	}
	memset(dist, -1, sizeof dist);
	int step = solve(start, row, col, dirty, 0, dirty.size());
	printf("%d\n", step);
	return EXIT_SUCCESS;
}

