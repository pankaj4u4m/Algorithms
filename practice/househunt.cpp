
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
int a;
int s;
char name[40];
int main ( int argc, char *argv[] ) {
	//freopen("househunt.cppin","r",stdin);
	PI start, end;
	int e;
	scanf("%d%d%d%d%d%d%d", &a, &s, &start.first, &start.second, &end.first, &end.second, &e);
	PI amenity[s+5][s+5];
	memset(amenity, 0, sizeof amenity);
	int cost[s+5][s+5];
	memset(cost, 0, sizeof cost);
	int dist[s+5][s+5];
	memset(dist, -1, sizeof dist);
	int x, y, p, d;
	for(int i = 0;i < e;++i){
		scanf("%s%d%d%d%d",name, &x, &y, &p, &d);
		amenity[x][y] = MP(p, d);
		cost[x][y] = -1;
	}
	int mnDist = INF;
	int mn = INF;
	for(int i = 0;i<s;++i){
		for(int j = 0;j<s;++j){
			if(cost[i][j] == 0){
				for(int k = 0;k<s;++k){
					for(int l = 0;l<s;++l){
						int v = amenity[k][l].first - (abs(i-k)+abs(j-l)) * amenity[k][l].second; 
						cost[i][j] += v>0?v:0;
					}
				}
				dist[i][j] = abs(abs(i-start.first) + abs(j-start.second) 
					- abs(i-end.first) - abs(j-end.second));
				if(cost[i][j] <= a){
					if(mnDist > dist[i][j]){
						mnDist = dist[i][j];
						mn = cost[i][j];
					} else if(mnDist == dist[i][j]){
						mn = min(mn, cost[i][j]);
					}
				}
			}
		}
	}
//	out(mnDist);
	PI pos = MP(INF, INF);
	for(int i = 0;i <s; ++i){
		for(int j = 0;j <s ; ++j){
			if(mnDist == dist[i][j] && mn == cost[i][j]){
				if(pos.first > i){
					pos = MP(i, j);
				} else if(pos.first == i && pos.second > j){
					pos = MP(i, j);
				}
			}
		}
	}

	//for(int i = 0;i<s;++i){
		//outA(cost[i], s);
	//}
	//for(int i = 0;i<s;++i){
		//outA(dist[i], s);
	//}

	//out(mn)out(pos.first)out(pos.second);
	if(mn <= a){
		printf("(%d,%d) : %d\n", pos.first, pos.second, mn);
	} else {
		printf("NO\n");
	}

	return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */
