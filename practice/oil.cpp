
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
int n;
char source[35];
char dest[35];
int T[60];
int cost[60];
double p[60];
map<string, int> vertex;
map<int, vector<PI> > graph;
int t, c;
int v = 1;
class State {
	public:
	int cost;
	int time;
	double prob;
	int ver;
	public:
	State(int c, int t, double p, int v){
		cost = c;
		time = t;
		prob = p;
		ver = v;
	}
};
double dijkstra(int s, int d){
	//cost, time, probability, vertex
	queue< State > q;
	q.push(State(0, 0, 1, s));
	bool visited[v+5];memset(visited, 0, sizeof visited);
	double mn = INF;
	while(!q.empty()){
		State top = q.front();q.pop();
		visited[top.ver] = true;
		if(top.ver == d){
			mn = min(mn, top.cost + (1-top.prob) * c);
		}
		FE(it, graph[top.ver]){
			if(!visited[it->first] && top.time + T[it->second] <= t){
				q.push(State(top.cost + cost[it->second], top.time + T[it->second], 
					top.prob * p[it->second], it->first));
			}
		}
	}
	return mn;
}
int main ( int argc, char *argv[] ) {
	//freopen("oil.cppin","r",stdin);
	scanf("%d", &n);
	for(int i = 0; i< n; ++i){
		scanf("%s%s%d%d%lf", source, dest, &T[i], &cost[i], &p[i]);
		p[i]= 1-p[i]/100.0;
		string s = source;
		string d = dest;
		if(vertex[s] == 0){
			vertex[s] = v++;
		}
		if(vertex[d] == 0){
			vertex[d] = v++;
		}
		int x = vertex[s];
		int y = vertex[d];
		graph[x].push_back(MP(y, i));
		//graph[y].push_back(MP(x, i));
	}
	scanf("%s%s%d%d", source, dest, &t, &c);
	string s = source;
	string d = dest;
	double res = dijkstra(vertex[s], vertex[d]);
	printf("%lf\n", res);
	return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */
