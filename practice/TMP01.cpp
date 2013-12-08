
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

vector<char> st;
int len;
int n;
struct comparator {
    bool operator() (int o1, int o2) const{
         int a = o1;
         int b = o2;
         while (a < n && b < n) {
					 out(st[a])out(st[b]);
             if(st[a] < st[b]){
                return true;
						 } else if(st[a] > st[b]){
							 return false;
						 }
						 a++;
						 b++;
						 out(a)out(b)
				 }
				 out(n)
         if (b != n) {
             return true;
         }
         return false;
    }
};


    int matchAll(int start1, int start2) {
        int a = start1;
        int b = start2;
        int c = 0;
        while (a < n && b < n) {
            if (st[a] == st[b]) {
                a++;
                b++;
                c++;
            } else {
                break;
            }
        }
        return c;
    }
int main ( int argc, char *argv[] ) {
	freopen("gen.cppin","r",stdin);
    /**
     * @param args
     * @throws IOException
     * @throws NumberFormatException
     */
        int q;scanf("%d", &q);
        long long res = 0;
        long long current = 0;
        set<int, comparator> suffix;
				len = 0;
				n=0;
        int removed = 0;
        vector<int> *matchedSuffix = new vector<int>();
        vector<int> *lastMatched = new vector<int>();
				char in[2];
        for (int i = 0; i < q; ++i) {
            scanf("%s", in);
            if (in[0] == '+') {
								scanf("%s", in);
                st.push_back(in[0]);
								++n;
                suffix.insert(len);

                current += len - removed + 1;

                vector<int> *matched = new vector<int>();
                vector<int> *last = new vector<int>();
								
								int sz = (*matchedSuffix).size();
                for (int m = 0; m < sz; ++m) {
									if((*matchedSuffix)[m] > removed){
                    set<int>::iterator higher = suffix.upper_bound((*matchedSuffix)[m]);
                    if (higher != suffix.end()) {
                        if (st[(*higher) + (*lastMatched)[m]] ==
                                st[(*matchedSuffix)[m] + (*lastMatched)[m]]) {
                            (*matched).push_back((*matchedSuffix)[m]);
                            (*last).push_back((*lastMatched)[m] + 1);
                            current -= 1;
                        }
                    }
									}
                }

                set<int>::iterator h = suffix.upper_bound(len);
                if (h != suffix.end()) {
                    if (st[*h] == st[len]) {
                        (*matched).push_back(len);
                        (*last).push_back(1);
                        current -= 1;
                    }
                }

								delete matchedSuffix;
								delete lastMatched;

                matchedSuffix = matched;
                lastMatched = last;
								
                len++;

                res += current;
                res %= 1000000007;
            } else {

                current -= len - removed;

                set<int>::iterator h = suffix.find(removed);
                if (h != suffix.begin() && h != suffix.end()) {
									  set<int>::iterator hh = h;
										--hh;
                    int c = matchAll(*hh, removed);
                    if (c != 0) {
                        current += c;
                    }
                }

                h = suffix.find(removed);
								out(*h)
								out(removed); 
						outC(suffix);out(suffix.size());
                suffix.erase(removed);
						outC(suffix);out(suffix.size());
								removed++;
                res += current;
                res %= 1000000007;

            }
						if(res > 570){
							break;
						}
            for(int i = removed ; i< n; ++i){
							cout<<st[i];
						}
						cout<<endl;
						outC(suffix);
            cout<<current<<":"<<res<<endl;
        }

        cout<<res % 1000000007<<endl;

	return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */
