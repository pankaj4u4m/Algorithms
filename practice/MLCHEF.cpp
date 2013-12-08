
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

map<int, vector<int> > graph;
map<int, int> parent;

void relaf(int chef[], int a, int v){
	chef[parent[a]] -= v;
	if(parent[a] != 0){
		relaf(chef, parent[a], v);
	}
}

int reduce(int health[], int chef[], int a, int x){
	vector<int> child = graph[a];
	int n = child.size();
	int res = 0;
	vector<int> remove;
	for(int i = 0; i< n; ++i){
		health[child[i]] -= x;
		if(health[child[i]] <= 0){
			remove.push_back(i);
		} else {
			res += 1;
		}
		res += reduce(health, chef, child[i], x);
	}

	int s = remove.size();
	int next = 0;
	vector<int>newChild;
	for(int i = 0; i< n; ++i){
		if(next < s && i == remove[next]){
			next++;
		} else {
			 newChild.push_back(child[i]);
		}
	}

	graph[a] = newChild;
	
	relaf(chef, a, chef[a] - res);
	chef[a] = res;
	return res;
}


int query(int health[], int poisoned[], int a, int r){
	int res = 0;
	vector<int> child = graph[a];
	int n = child.size();
	for(int i = 0; i< n; ++i){
		res += ((health[child[i]] - r) > 0? 1: 0);
		res += query(health, poisoned, child[i], r + poisoned[child[i]]);
	}
	return res;
}

int main ( int argc, char *argv[] ) {
	//freopen("MLCHEF.cppin","r",stdin);
	int n;scanf("%d", &n);
	int health[n+1];
	int chef[n+1];

	memset(chef, 0, sizeof chef);
	
	for(int i = 0; i< n; ++i){
		int h, a;scanf("%d%d", &h, &a);
		health[i+1] = h;
		graph[a].push_back(i+1);
		parent[i+1] = a;
	}

	int q;scanf("%d", &q);
	
	reduce(health, chef, 0, 0);

	for(int i = 0; i< q; ++i){
		int t;scanf("%d", &t);
		if(t == 1){
			int a, x;scanf("%d%d", &a, &x);
			reduce(health, chef, a, x);
		} else {
			int a;scanf("%d", &a);
			printf("%d\n", chef[a]);
		}
	}

	return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */
