#include <string>
#include <vector>
#include <map>
#include <set>
#include <algorithm>
#include <sstream>
#include <iostream>
#include <functional>
#include <cctype>
#include <complex>
#include <cmath>
#include <cstdio>
#include <cstdlib>
#include <cassert>
#include <queue>
#include <stack>
#include <list>
#include <iomanip>
using namespace std;
class PackageShipping {public: double ship(vector <string> routes, string origin, string destination, int time, int packageCost); };
 
struct edge
{
  int to, time, cost;
  double prob;
 
  edge(int to, int time, int cost, double prob) : to(to), time(time), cost(cost), prob(prob) {}
};
 
typedef vector<edge>  VE;
typedef vector<VE>    VVE;
 
struct state
{
  int pos, time, cost;
  double prob;
 
  state(int pos, int time, int cost, double prob) : pos(pos), time(time), cost(cost), prob(prob) {}
};
 
struct reached
{
  int cost;
  double prob;
  reached(int cost, double prob) : cost(cost), prob(prob) {}
};
 
double value;
 
bool operator< (const state &a, const state &b)
{
  return (a.cost + (1.0 - a.prob) * value) > (b.cost + (1.0 - b.prob) * value);
}
 
double PackageShipping::ship(vector <string> routes, string origin, string destination, int time, int packageCost)
{
  value = packageCost;
  map<string,int> ind;
  int n = 0;
  VVE adj;
	int rs = routes.size();
  for (int i = 0; i < rs; i++)
  {
    stringstream buf(routes[i]);
    string x, y;
    int t, c;
    double p;
    buf >> x >> y >> t >> c >> p;
    if (ind.find(x) == ind.end())
    {
      ind[x] = n++;
      adj.push_back(VE());
    }
    if (ind.find(y) == ind.end())
    {
      ind[y] = n++;
      adj.push_back(VE());
    }
    adj[ind[x]].push_back(edge(ind[y], t, c, 1.0 - p / 100.0));
  }
 
  vector<vector<reached> > there(n);
  int end = ind[destination];
  priority_queue<state> pq;
  pq.push(state(ind[origin], 0, 0, 1.0));
  while (!pq.empty())
  {
    state act = pq.top();
    pq.pop();
 
    if (act.time > time)
      continue;
    bool skip = false;
    for (vector<reached>::iterator jt = there[act.pos].begin(); jt != there[act.pos].end(); ++jt)
      if (jt->cost <= act.cost && jt->prob >= act.prob)
      {
        skip = true;
        break;
      }
    if (skip)
      continue;
    there[act.pos].push_back(reached(act.cost, act.prob));
 
    if (act.pos == end)
      return act.cost + (1.0 - act.prob) * value;
    for (VE::iterator it = adj[act.pos].begin(); it != adj[act.pos].end(); ++it)
    {
      state next(it->to, act.time + it->time, act.cost + it->cost, act.prob * it->prob);
      if (next.time > time)
        continue;
      pq.push(next);
    }
  }
  return -1.0;
}

