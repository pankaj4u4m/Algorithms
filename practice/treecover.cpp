
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
map<int, set<int> > graph;
bool covered[2*100000+5];
int parent[2*100000+5];
bool visited[2*100000+5];

int getFarest(int a){
	set<PI> q;
	q.insert(MP(0, a));
	int p = 0;
	memset(visited, 0, sizeof visited);
	while(!q.empty()){
		PI top = *q.begin();q.erase(q.begin());
		if(!visited[top.second]){
			visited[top.second] = true;
			parent[top.second] = p;
			p = top.second;
			FE(it, graph[top.second]){
				if(!visited[*it]){
					q.insert(MP(top.first + (!covered[*it] ? 1:0), *it));
				}
			}
		}
	}
	return p;
}
void bfs(int a){
	set<pair<PI, int> > q;
	q.insert(MP(MP(0, a), 0));
	int p = 0;
	memset(visited, 0, sizeof visited);
	while(!q.empty()){
		pair<PI, int > v = *q.begin();q.erase(q.begin());
		PI top = v.first;
		if(!visited[top.second]){
			visited[top.second] = true;
			parent[top.second] = v.second;
			p = top.second;
			FE(it, graph[top.second]){
				if(!visited[*it]){
					q.insert(MP(MP(top.first + (!covered[*it] ? 1:0), *it), top.second));
				}
			}
		}
	}
}

set<PI> dist;
PI mp[2*100000+5];
PI dfs(int a){
	visited[a] = true;
	PI mx = MP(0, a);
	FE(it, graph[a]){
		if(!visited[*it]){
			PI r = dfs(*it);
			if(r.first > mx.first){
				mx = r;
			}
		}	
	}
	mx.first+=1;
	dist.insert(mx);
	mp[a] = mx;
//	cout<<mx.first<<"="<<mx.second<<endl;
	return mx;
}

int process(){
	PI v = *dist.rbegin();
	int p = v.second;
	int res = v.first;
	while(p != 0){
		dist.erase(mp[p]);
		int t = parent[p];
		parent[p] = 0;
		p = t;
		v = mp[p];
	}
	return res;
}

int main ( int argc, char *argv[] ) {
	//freopen("treecover.cppin","r",stdin);
	//freopen("treecover2.cppin","r",stdin);
	//freopen("treecover3.cppout","w",stdout);
	//freopen("treecover1000.cppout","w",stdout);
	scanf("%d", &n);
	int x, y;
	for(int i = 0; i< n-1; ++i){
		scanf("%d%d", &x, &y);
		graph[x].insert(y);
		graph[y].insert(x);
	}

	int a = getFarest(1);
	bfs(a);
	memset(visited, 0, sizeof visited);
	dfs(a);

	printf("1\n");
	int res = 0;
	for(int i = 1; i< n; ++i){
		if(res < n){
			res += process();
		}
		printf("%d\n", res);
	}
	return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */

