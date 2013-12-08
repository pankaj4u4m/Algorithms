
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

#include<time.h>

int main ( int argc, char *argv[] ) {
	freopen("nearby.cppin","w",stdout);
	srand(time(NULL));
	int t=10000, q=1000, n=10000;
	printf("%d %d %d\n", t, q, n);
	for(int i =0;i<t;++i){
		double x = rand()%1000;
		double y = rand()%1000;
    printf("%d %lf %lf\n", i, abs(x*x*x)/1000, abs(y*y*y)/1000);
	}
	for(int i = 0; i< q; ++i){
		int qn = rand()%100;
		printf("%d %d ", i, qn);
		for(int j = 0; j< qn; ++j){
			printf("%d ", rand()%t);
		}
		puts("");
	}
	for(int i = 0; i< n; ++i){
		char ch = 't';
		if(rand()&1){
		//	ch= 'q';
		}
		double x = rand()%1000000000;
		double y = rand()%1000000000;
		printf("%c %d %lf %lf\n", ch, rand()%100, x/1000, y/1000);
	}
	return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */
