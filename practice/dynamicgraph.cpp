
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

int n, e;
map<int, vector<PI> > graph;
int t1, t2;
long long getMst(){
	set<PI> q;
	q.insert(MP(0, 1));
	bool visited[n+5];
	memset(visited, 0, sizeof visited);

	long long sum = 0;

	while(!q.empty()){
		PI top = *q.begin();q.erase(q.begin());
		if(!visited[top.second]){
			visited[top.second] = true;
			sum += top.first;
			FE(it, graph[top.second]){
				if(!visited[it->second]){
					q.insert(*it);
				}
			}
		}
	}
	return sum;
}
int main ( int argc, char *argv[] ) {
	//freopen("dynamicgraph.cppin","r",stdin);
	scanf("%d%d", &n, &e);
	int u, v, w;
	for(int i = 0; i< e; ++i){
		scanf("%d%d%d", &u, &v, &w);
		graph[u].push_back(MP(w, v));
		graph[v].push_back(MP(w, u));
	}
	scanf("%d%d", &t1, &t2);
	long long mst = getMst();
	if(mst <= 0){
		printf("%d\n", t1);
	} else {
		printf("%d\n", t2);
	}
	return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */
