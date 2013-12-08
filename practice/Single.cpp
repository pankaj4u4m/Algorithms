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

char st[1005];

int n;
struct comparator {
    bool operator() (int o1, int o2) const{
         int a = o1;
         int b = o2;
         while (a < n && b < n) {
					 //out(a)out(b)out(st[a])out(st[b]);
             if(st[a] < st[b]){
                return true;
						 } else if(st[a] > st[b]){
							 return false;
						 }
						 a++;
						 b++;
				 }
         if (b != n) {
             return true;
         }
         return false;
    }
};

int matched(int a, int b){
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

long long getSubstringcount(){

		set<int, comparator> suffix;
		n = strlen(st);
		for(int i = 0; i< n; ++i){
			suffix.insert(i);
		}
	//	outC(suffix);
		
		long long res  =0;

		set<int>::iterator prev = suffix.begin();

		res += n-*prev;
		set<int>::iterator it = prev;

		outC(suffix);
		
		for(++it; it != suffix.end(); ++it){
			res += n - *it;
			res -= matched(*it, *prev);
			prev = it;
			out(res);
		}
		return res;
}

int main ( int argc, char *argv[] ) {
	//freopen("gen.cppin","r",stdin);
	int T;scanf("%d", &T);
	//string str = "";
	//long long res = 0;
  for (int i = 0; i < T; ++i) {
      //char in[2];scanf("%s", in);
      //if (in[0] == '+') {
				//	scanf("%s", in);
					//str += in;
			//} else {
				//	str.erase(str.begin());
			//}
		//cout<<st<<" ";
		
		//strcpy(st, str.c_str());
		
		scanf("%s", st);

		long long current =  getSubstringcount(); 
		//res +=current;
		cout<<current<<endl;
		//cout<<current<<":"<<res<<endl;
	}
	//cout<<res<<endl;
	return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */


