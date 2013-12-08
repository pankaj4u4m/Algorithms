//	Filename:  COUNTPAL.cpp 
//	Author:  pankaj kumar, pankaj4u4m@gmail.com
#include <cassert>//c headers in c++
#include <cctype>
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
#include <iterator>
#include <limits>
#include <list>
#include <map>
#include <numeric>
#include <queue>
#include <set>
#include <stack>
#include <string>
#include <utility>
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
#define VS      V(string)
#define PI      pair< int,int >
#define MP      make_pair
#define INF 	2000000000

typedef long long LL;
typedef unsigned long long ULL;
typedef long double LD;

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
#define INPUT fread_unlocked(IBUF, 1, BUFSIZE, stdin);
#define DIG(a) (((a)>='0')&&((a)<='9'))
#define getChar(t) {t=*inputptr++;}
template<class T>inline bool getInt(T &j){j=0;int _t;getChar(_t);if(_t==0)return false;char sign;while(!DIG(_t)&&_t!=0){sign=_t;getChar(_t);}while(DIG(_t)){j=10*j+(_t-'0');getChar(_t);}if(sign == '-') j = -j;*inputptr--;return j==0&&_t==0?false:true;}
inline bool getString(char *s, char n = 32){char _c;getChar(_c);if(_c==0)return false;while(_c==10||_c==n)getChar(_c);while(_c != 10&&_c != n&&_c!=0){*s++=_c;getChar(_c)}*s=0;inputptr--;return s[0]==0&&_c==0?false:true;}
#endif
//#define PUT
#ifdef PUT
char OBUF[BUFSIZE+1], *outputptr=OBUF;
template<class T> inline void putInt(T x, char n=0){int y, dig=0;if(x<0){*outputptr++='-';x=-x;}while(x||!dig){y=x%10;DIP[dig++]=y+'0';x/=10;}while (dig--) *outputptr++=DIP[dig];n?*outputptr++=n:0;}
template<class T> inline void putString(T *s, char n=0){while(*s)*outputptr++=*s++;n?*outputptr++=n:0;}
#define putChar(c) {*outputptr++=c;}
#define OUTPUT fputs(OBUF, stdout);
#endif
/*END TEMPLATE:CODEGAMBLER*/
bool found = false;

struct Node {
	Node *left;
	Node *right;
	int value;
	public:
	Node(int v){
		value = v;
		left = NULL;
		right = NULL;
	}
	Node* search(int *value){
		Node *ret = NULL;
		if(this->left != NULL){
			ret = this->left->search(value);
		}
		if(found){
			return this;
		}
		if(this->value == *value) {
			found = true;
		}
		if(this->right != NULL && ret == NULL){
			ret = this->right->search(value);
		}
		return ret;
	}
};

int main(){
//	freopen("COUNTPAL.cppin","r",stdin);
//	freopen("COUNTPAL_T.cppin","r",stdin);
	Node *tree = new Node(4);
	tree->left = new Node(2);
	tree->right = new Node(5);
	tree->left->right = new Node(3);
	int v = 3;
	cout<<tree->search(&v)->value;
	return 0;
}


