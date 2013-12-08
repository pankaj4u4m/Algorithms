//	Filename:  MONEY1.cpp 
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
#define VAR(x,a) 	__typeof(a) x=(a)
#define FE(it,c)	for(VAR(it,(c).begin());it!=(c).end();++it)
#define FOR(i,a,b)  	for(int i=(int)(a),_b=(int)(b) ; i < _b;++i)
#define FORR(i,a,b) 	for(int i=(a),_b=(b);i>=_b;--i)
#define	REP(i,n)    	FOR(i,0,n)
#define ALL(c)		(c).begin(),(c).end()
#define SZ(c)		(int)(c).size()
#define PB      push_back
#define V(x)    vector< x >
#define VI      V(int)
#define VVI     V(VI)
#define VS      V(string)
#define PI      pair< int,int >
#define MP      make_pair
#define pi      3.1415926535897932384626433832795
#define INF 	2000000000

const double eps=1e-11;
typedef long long LL;
typedef unsigned long long ULL;
typedef long double LD;

template<class T> inline T		lowbit(T n)		{return (n^(n-1))&n;}
template<class T> inline int	countbit(T n)	{return (n==0)?0:(1+countbit(n&(n-1)));}
template<class T> inline T 		sqr(T x)		{return x*x;}
template<class T> inline T 		gcd(T a,T b)	{if(a<0)return gcd(-a,b);if(b<0)return gcd(a,-b);while (b > 0){a = a % b;a ^= b;b ^= a; a ^= b; } return a;}
template<class T> inline T 		lcm(T a,T b)	{if(a<0)return lcm(-a,b);if(b<0)return lcm(a,-b);return a*(b/gcd(a,b));}
template<class T> inline T 		eularFunction(T n)		{vector<pair<T,int> > R=factorize(n);T r=n;for (int i=0;i<R.size();i++)r=r/R[i].first*(R[i].first-1);return r;}
template<class T> inline T 		euclide(T a,T b,T &x,T &y)		{if(a<0){T d=euclide(-a,b,x,y);x=-x;return d;}if(b<0){T d=euclide(a,-b,x,y);y=-y;return d;}if(b==0){x=1;y=0;return a;}else{T d=euclide(b,a%b,x,y);T t=x;x=y;y=t-(a/b)*y;return d;}}
template<class T> static void 	split(const string &s, vector<T> &out)	{istringstream in(s);out.clear();copy(istream_iterator<T>(in), istream_iterator<T>(), back_inserter(out));}

/*Point&Line*/
template<class T> T cross(T x0,T y0,T x1,T y1,T x2,T y2){return (x1-x0)*(y2-y0)-(x2-x0)*(y1-y0);}
int crossOper(double x0,double y0,double x1,double y1,double x2,double y2){double t=(x1-x0)*(y2-y0)-(x2-x0)*(y1-y0);if (fabs(t)<=eps) return 0;return (t<0)?-1:1;}
bool isIntersect(double x1,double y1,double x2,double y2,double x3,double y3,double x4,double y4){return crossOper(x1,y1,x2,y2,x3,y3)*crossOper(x1,y1,x2,y2,x4,y4)<0 && crossOper(x3,y3,x4,y4,x1,y1)*crossOper(x3,y3,x4,y4,x2,y2)<0;}
bool isMiddle(double s,double m,double t){return fabs(s-m)<=eps || fabs(t-m)<=eps || (s<m)!=(t<m);}

/* Only for Debugging */
#define out(__debug) cout << #__debug<< "=" <<__debug << endl;
#define outC(A) cout<<#A<<"={"; FE(__it,A)cout << *__it << " " ;cout<<"}"<<endl;
template<class T>inline void outA(T A[], int n) {cout<<"{"; REP (i, n)cout<<A[i]<<" "; cout<<"}"<<endl;}

/* Input Output Function */
#define BUFSIZE (1<<26)
char DIP[20];
#define GET
#ifdef GET
char IBUF[BUFSIZE+1], *inputptr=IBUF;
#define INPUT fread(IBUF, 1, BUFSIZE, stdin);
#define DIG(a) (((a)>='0')&&((a)<='9'))
#define getChar(t) {t=*inputptr++;}
template<class T>inline bool getInt(T &j){j=0;int _t;getChar(_t);if(_t==0)return false;char sign;while(!DIG(_t)&&_t!=0){sign=_t;getChar(_t);}while(DIG(_t)){j=10*j+(_t-'0');getChar(_t);}if(sign == '-') j = -j;*inputptr--;return j==0&&_t==0?false:true;}//::getInt(
inline bool getString(char *s, char n = 32){char _c;getChar(_c);if(_c==0)return false;while(_c==10||_c==n)getChar(_c);while(_c != 10&&_c != n&&_c!=0){*s++=_c;getChar(_c)}*s=0;inputptr--;return s[0]==0&&_c==0?false:true;}
inline bool getLine(char *s){return getString(s, 10);}
#endif
//#define PUT
#ifdef PUT
char OBUF[BUFSIZE+1], *outputptr=OBUF;
template<class T> inline void putInt(T x, char n=0){int y, dig=0;if(x<0){*outputptr++='-';x=-x;}while(x||!dig){y=x%10;DIP[dig++]=y+'0';x/=10;}while (dig--) *outputptr++=DIP[dig];n?*outputptr++=n:0;}
template<class T> inline void putString(T *s, char n=0){while(*s)*outputptr++=*s++;n?*outputptr++=n:0;}
#define putChar(c) {*outputptr++=c;}
#define putLine *outputptr++=10;
#define OUTPUT fputs(OBUF, stdout);
#endif
/*END TEMPLATE:CODEGAMBLER*/
 
multiset<int> factorize(int n){
	multiset<int> p;
	int c = 0;
	if(n%2 == 0){
		c = 0;
		while(n%2 == 0){
			n/=2;
			c++;
		}
		p.insert(c);
	}
	if(n%3 == 0){
		c = 0;
		while(n%3 == 0){
			n/=3;
			c++;
		}
		p.insert(c);
	}
	if(n%5 == 0){
		c = 0;
		while(n%5 == 0){
			n/=5;
			c++;
		}
		p.insert(c);
	}
	int i = 7;
	int j = 11;
	int s = sqrt(n)+1;
	for(;i<=s; i+=6, j+=6){
		if(n%i == 0){
			c = 0;
			while(n%i == 0){
				n/=i;
				c++;
			}
			p.insert( c);
		}
		if(n%j == 0){
			c = 0;
			while(n%j == 0){
				n/=j;
				c++;
			}
			p.insert(c);
		}
	}
	if(n>1){
		p.insert(1);
	}
	return p;
}
map<multiset<int>, int> mem;
int value(multiset<int> p){
	int ans = 1;
	int sz = SZ(p);
	if(sz == 1){
		return 1<<(*p.begin()-1);
	}
	vector<multiset<int> > t;
	vector<multiset<int> > q;
	FE(i, p){
		REP(j, *i){
			multiset<int> a;
			a.insert(j+1);
			q.PB(a);
			FE(it, t){
				multiset<int> temp(*it);
				temp.insert(j+1);
				q.PB(temp);
			}
		}
		FE(it, q)t.PB(*it);
		q.clear();
	}
	int s = SZ(t);
	REP(i, s-1){
		if(mem[t[i]] == 0){
			mem[t[i]] = value(t[i]);
		}
		ans+=mem[t[i]];
	}
	return ans;
}
int solve(int n){
	multiset<int> p = factorize(n);
//	outC(p);
	return value(p);
//	return 0;
}
int main(){
//	freopen("MONEY.cppin","r",stdin);
//	INPUT;
	int T;
	scanf("%d", &T);
//	getInt(T);
	while(T--){
	//	FOR(n, 2, 1001)
		int n; 
		scanf("%d", &n);
//		getInt(n);
		printf("%d\n", solve(n+1));
	}
//	cout<<float(clock())/CLOCKS_PER_SEC;
	return 0;
}


