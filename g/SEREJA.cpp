
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
int A[100005];
//int cnt = 0;
int n;
const long long int mod = 1000000007 ;
long long int pow(long long int a, int b, long long int m){
	long long int p = 1;
	while(b){
		if(b&1){
			p*=a;p%=m;
		}
		a*=a;
		a%=m;
		b>>=1;
	}
	return p;
}
long long int fun(int a, int b, int index){
	if(a >= b || a<0 || b >=n){
		return 1;
	}
	if(b-a == 1){
	//	cnt++;
		return abs(A[b]- A[a]);
	}
	//out(a)//out(b);
	int mn = A[index];
	//out(index)
	int leftCount = 1;
	int rightCount = 1;
	int i = index +1;
	int j = index -1;
	int rightmx = A[i];
	int leftmx = A[j];
	
	int rightmn = A[i];
	int rightindex = i;
	int leftmn = A[j];
	int leftindex = j;


	long long int ans = 1;
	//out(right)
	//out(left)
	while(i <= b || j>= a){
//		c//out<<right<< " "<<i<<" " <<left<<" " << j<< " "<<endl;
		if(i <= b && (j < a || rightmx <= leftmx)){
			rightCount += 1;
			//out(leftCount)
				//out(maxcount[i])
			//cnt+=leftCount * maxcount[i];
			ans *= pow((long long int)rightmx - mn, leftCount, mod)	;	
			ans%=mod;
		
			i++;
			if(i <= b){
				if(rightmx < A[i]){
					rightmx = A[i];
				} else if(rightmn > A[i]){
					rightmn = A[i];
					rightindex = i;
				}
			}
//		c//out<<right<< " "<<i<<" " <<left<<" " << j<< " "<<endl;
		} else {
//		while(j>=left && (i> right || maxtree[j] <= maxtree[i])){
			leftCount += 1;
			//out(rightCount)
				//out(maxcount[j])
			//cnt+=rightCount * maxcount[j];
			ans *= pow((long long int)leftmx - mn, rightCount, mod)	;	
			ans%=mod;
			
			j--;
			if(j >= a){ 
				if(leftmx < A[j]){
					leftmx = A[j];
				} else if(leftmn > A[j]){
					leftmn = A[j];
					leftindex = j;
				}
			}
//		c//out<<right<< " "<<i<<" " <<left<<" " << j<< " "<<endl;
		}
	}
	//out(cnt)
	//outA(maxtree, b+1);
	//outA(maxcount, b+1);

	ans*= fun(a, index-1, leftindex);ans%=mod;
	ans*= fun(index+1, b, rightindex);ans%=mod;
	return ans;
}

int main ( int argc, char *argv[] ) {
//	freopen("SEREJA.cppin","r",stdin);
//	freopen("seggen1.cppin","r",stdin);//count=1999000 726616266
//	freopen("seggen2.cppin","r",stdin);//count=50989851 755639172
//	freopen("seggen.cppin","r",stdin);
//	freopen("seggen3.cppin","r",stdin); //cnt = 15 572049818 
//	freopen("seggen4.cppin","r",stdin); 
//	freopen("seggen10.cppin","r",stdin); //cnt = 45 663588630
//	int T = 1;
//	scanf("%d", &T);
//	while(T-->0){
		INPUT;
		//cnt = 0;
		//scanf("%d", &n);
		getInt(n);
		int mn = INF;
		int index = 0;
		for(int i = 0; i< n; ++i){
//			scanf("%d", &A[i]);
			getInt(A[i]);
			if(mn > A[i]){
				mn = A[i];
				index = i;
			}
		}
		long long int ans = fun(0, n-1, index)%mod;
//		out(cnt);
		printf("%d\n", (int)ans);
//	}
	return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */
