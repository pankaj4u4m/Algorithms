
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

template<class T> inline T lowbit(T n){return (n^(n-1))&n;}//::lowbit(
template<class T> inline int countbit(T n){return (n==0)?0:(1+countbit(n&(n-1)));}//::countbit(
template<class T> inline T sqr(T x){return x*x;}//::sqr
template<class T> inline T gcd(T a,T b)//::gcd(
{if(a<0)return gcd(-a,b);if(b<0)return gcd(a,-b);while (b > 0){a = a % b;a ^= b;b ^= a; a ^= b; } return a;}
template<class T> inline T lcm(T a,T b)//::lcm(
{if(a<0)return lcm(-a,b);if(b<0)return lcm(a,-b);return a*(b/gcd(a,b));}
template<class T> inline T euclide(T a,T b,T &x,T &y)//::euclide(
{if(a<0){T d=euclide(-a,b,x,y);x=-x;return d;}if(b<0){T d=euclide(a,-b,x,y);y=-y;return d;}if(b==0){x=1;y=0;return a;}else{T d=euclide(b,a%b,x,y);T t=x;x=y;y=t-(a/b)*y;return d;}}
template<class T> inline vector<pair<T,int> > factorize(T n)//::factorize(
{vector<pair<T,int> > R;for (T i=2;n>1;){if (n%i==0){int C=0;for (;n%i==0;C++,n/=i);R.push_back(make_pair(i,C));}i++;if (i>n/i) i=n;}if (n>1) R.push_back(make_pair(n,1));return R;}
template<class T> inline T eularFunction(T n)//::eularFunction(
{vector<pair<T,int> > R=factorize(n);T r=n;for (int i=0;i<R.size();i++)r=r/R[i].first*(R[i].first-1);return r;}
template<class T> static void split(const string &s, vector<T> &out)//::split(
{istringstream in(s);out.clear();copy(istream_iterator<T>(in), istream_iterator<T>(), back_inserter(out));}
template<class T> inline T multiplyMod(T a,T b,T m) {return (T)((((LL)(a)*(LL)(b)%(LL)(m))+(LL)(m))%(LL)(m));}//::multiplyMod(
template<class T> inline T powerMod(T p,int e,T m)//::powerMod(
{if(e==0)return 1%m;else if(e%2==0){T t=powerMod(p,e/2,m);return multiplyMod(t,t,m);}else return multiplyMod(powerMod(p,e-1,m),p,m);}

/*Point&Line*/
template<class T> T cross(T x0,T y0,T x1,T y1,T x2,T y2){return (x1-x0)*(y2-y0)-(x2-x0)*(y1-y0);}//::cross(
int crossOper(double x0,double y0,double x1,double y1,double x2,double y2)//::crossOper(
{double t=(x1-x0)*(y2-y0)-(x2-x0)*(y1-y0);if (fabs(t)<=eps) return 0;return (t<0)?-1:1;}
bool isIntersect(double x1,double y1,double x2,double y2,double x3,double y3,double x4,double y4)//::isIntersect(
{return crossOper(x1,y1,x2,y2,x3,y3)*crossOper(x1,y1,x2,y2,x4,y4)<0 && crossOper(x3,y3,x4,y4,x1,y1)*crossOper(x3,y3,x4,y4,x2,y2)<0;}
bool isMiddle(double s,double m,double t){return fabs(s-m)<=eps || fabs(t-m)<=eps || (s<m)!=(t<m);}//::isMiddle(

/* Only for Debugging */
#define out(__debug) cout << #__debug<< "=" <<__debug << endl;//::out(
#define outC(A) cout<<"{"; FE(it,A)cout << *it << " " ;cout<<"}"<<endl;//::outContainer
template<class T>inline void outA(T A[], int n) {cout<<"{"; REP (i, n)cout<<A[i]<<" "; cout<<"}"<<endl;}//:outArray

/* Input Output Function */
#define BUFSIZE (1<<26)
char DIP[20];
//#define GET
#ifdef GET//::GET
char IBUF[BUFSIZE+1], *inputptr=IBUF;
#define INPUT fread_unlocked(IBUF, 1, BUFSIZE, stdin);//::INPUT
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

int main ( int argc, char *argv[] ) {
	//freopen("CIELDIST.cppin","r",stdin);
	int T;scanf("%d", &T);
	while(T-->0){
		int d1, d2, d3;
		scanf("%d%d%d", &d1, &d2, &d3);
		int d = d3-d2-d1;
		d = d<0?0:d;
		if(d1 == d3 || d2 == d3 ){
			printf("%d\n", d);
		} else {
			printf("%d.000000\n", d);
		}
	}
	return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */
