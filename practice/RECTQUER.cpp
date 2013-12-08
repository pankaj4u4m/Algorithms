
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
      

    int upper(vector<pair<int, int> > &value, int x2) {
        int start = 0;
        int end = value.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (value[mid].first <= x2) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }

    int lower(vector<pair<int, int> > &value, int x1) {
        int start = 0;
        int end = value.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (value[mid].first < x1) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
    bool inside(vector<pair<int, int> > &value, int x1, int y1, int x2,
            int y2) {
        int left = lower(value, x1);
        int right = upper(value, x2);
        for (int i = left; i <= right; ++i) {
            if (y1 <= value[i].second && value[i].second <= y2) {
                return true;
            }
        }
        return false;
    }

int main ( int argc, char *argv[] ) {
	//freopen("RECTQUER.cppin","r",stdin);
	
        int n;scanf("%d", &n); 
        map<int, vector<pair<int, int> > > point;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int a;scanf("%d", &a); 
                point[a].push_back(MP(i, j));
            }
        }
        FE(it, point){
					sort(it->second.begin(), it->second.end());
				}

        int q;scanf("%d", &q);
        int x1, x2, y1, y2;
        for (int i = 0; i < q; ++i) {
					scanf("%d%d%d%d", &x1, &y1, &x2, &y2);
            int res = 0;
            FE(it, point) {
                if (inside(it->second, x1-1, y1-1, x2-1, y2-1)) {
                    res++;
                }
            }
            printf("%d\n", res);
        }

	return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */
