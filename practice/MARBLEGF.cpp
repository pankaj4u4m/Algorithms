
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
    long long tree[1000005];
    int maxIdx = 1000005;

    void update(int idx, int s) {
        while (idx < maxIdx) {
            tree[idx] += s;
            idx += (idx & (-idx));
        }
    }

    long long read(int idx) {
        long long sum = 0;
        while (idx > 0) {
            sum += tree[idx];
            idx -= (idx & (-idx));
        }
        return sum;
    }

int main ( int argc, char *argv[] ) {
	//freopen("MARBLEGF.cppin","r",stdin);
	int n, q;scanf("%d%d", &n, &q);
	int a;for(int i = 0; i<n; ++i){scanf("%d", &a);update(i+1,  a);}
	char c[2];int x, y;
	for(int i = 0; i< q; ++i){
			scanf("%s%d%d", c, &x, &y);
			if(c[0] == 'S'){
				//printf("%lld", read(y+1) - read(x));
				cout<<(read(y+1) - read(x))<<"\n";
			} else if(c[0] == 'G'){
				update(x+1, y);
			} else {
				update(x+1, -y);
			}
	}
	return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */
