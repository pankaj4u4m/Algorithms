
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

/* Input Output Function */
#define BUFSIZE (1<<26)
char DIP[20];
#define GET
#ifdef GET//::GET
char IBUF[BUFSIZE+1], *inputptr=IBUF;
#define INPUT fread(IBUF, 1, BUFSIZE, stdin);//::INPUT
#define DIG(a) (((a)>='0')&&((a)<='9'))//::DIG(
#define getChar(t) {t=*inputptr++;}//::getChar(
template<class T>inline bool getInt(T &j) {j=0;int _t;getChar(_t);if(_t==0)return false;char sign;while(!DIG(_t)&&_t!=0){sign=_t;getChar(_t);}while(DIG(_t)){j=10*j+(_t-'0');getChar(_t);}if(sign == '-') j = -j;*inputptr--;return j==0&&_t==0?false:true;}//::getInt(
inline bool getString(char *s) {char _c;getChar(_c);if(_c==0)return false;while(_c==10||_c==32)getChar(_c);while(_c != 10&&_c != 32&&_c!=0){*s++=_c;getChar(_c)}*s=0;inputptr--;return s[0]==0&&_c==0?false:true;}//::getString(
#endif
//#define PUT
#ifdef PUT//::PUT
char OBUF[BUFSIZE+1], *outputptr=OBUF;
template<class T> inline void putInt(T x, char n=0) {int y, dig=0;if(x<0){*outputptr++='-';x=-x;}while(x||!dig){y=x%10;DIP[dig++]=y+'0';x/=10;}while (dig--) *outputptr++=DIP[dig];n?*outputptr++=n:0;}//::putInt(
template<class T> inline void putString(T *s, char n=0){while(*s)*outputptr++=*s++;n?*outputptr++=n:0;}//::putString(
#define putChar(c) {*outputptr++=c;}//::putChar(
#define putLine *outputptr++=10;//::putLine(
#define OUTPUT fputs(OBUF, stdout);//::OUTPUT
#endif
/**************** Main program *******************/
int n;
int F[10005];
int A[10005];
int G[10005];
int maxA;
int maxG;

bool fun(int i, int a, int g, int d, int r){
	if(i >= n){
		return true;
	}
	bool solved = false;

	if(!solved && (a<=1 || F[i] == A[a-1] + d) && (g<=1 || F[i] == G[g-1] * r)){
		A[a] = F[i];
		G[g] = F[i];
		maxA = max(maxA, a);
		maxG = max(maxG, g);
		
		d = a == 0 ? INF : A[a] - A[a-1];
		r = g == 0 ? INF : G[g]/G[g-1];
		solved = fun(i+1, a+1, g+1, d, r);
	}
	if(!solved && (a<=1 || F[i] == A[a-1] + d)){
		A[a] = F[i];
		maxA = max(maxA, a); 
		d = a == 0 ? INF : A[a] - A[a-1];
		solved = fun(i+1, a+1, g, d, r);
	}
	if(!solved && (g<=1 || F[i] == G[g-1] * r)){
		G[g] = F[i];
		maxG = max(maxG, g);
		r = g == 0 ? INF : G[g]/G[g-1];
		solved = fun(i+1, a, g+1, d, r);
	}
	return solved;
}
int main ( int argc, char *argv[] ) {
	freopen("ARIGEOM.cppin","r",stdin);
	INPUT;
	int T;//scanf("%d", &T);
	getInt(T);
	while(T-->0){
		//scanf("%d", &n);
		getInt(n);
		for(int i = 0; i< n;  ++i){
			//scanf("%d", &F[i]);
			getInt(F[i]);
		}
		maxA = 0; maxG = 0;
		int d = INF;
		int r = INF;
		fun(0, 0, 0, d, r);
		for(int i = 0; i<= maxA; ++i){
			printf("%d ", A[i]);
		}puts("");
		for(int i = 0; i<= maxG; ++i){
			printf("%d ", G[i]);
		}puts("");

	}
	return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */
