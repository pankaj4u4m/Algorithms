
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

typedef long long LL;//::LL
typedef unsigned long long ULL;//::ULL
typedef long double LD;//::LD

/* Only for Debugging */
#define out(__debug) cout << #__debug<< "=" <<__debug << endl;//::out(
#define outC(A) cout<<"{"; FE(it,A)cout << *it << " " ;cout<<"}"<<endl;//::outContainer
template<class T>inline void outA(T A[], int n) {cout<<"{"; REP (i, n)cout<<A[i]<<" "; cout<<"}"<<endl;}//:outArray

/**************** Main program *******************/

const double eps = 0.001;

struct Comparator {
    bool operator() (const pair<double, int> &a, const pair<double, int> &b) const{
			if(abs(a.first - b.first) < eps){
				return b.second < a.second;
			} else {
				return a.first < b.first;
			}
    }
};


pair<double, double> point[100005];
VI topic;
set<int, greater<int> > topicQuestion[100005];

#define SPD set<pair<double, int>, Comparator >  

set<pair<double, int>, Comparator > *currentPoints;

double sqr(double x){
	return x*x;
}

void getNearTopics(double x, double y){
	currentPoints = new SPD();
	FE(t, topic){
		pair<double, double> p = point[*t];
		double dis = sqrt(sqr(p.first - x) + sqr(p.second - y));
		currentPoints->insert(MP(dis, *t));
	}
}

int main ( int argc, char *argv[] ) {
//	freopen("nearby.cppin","r",stdin);
//	freopen("nearbydumb.cppout","w",stdout);
	int t, q, n;
	scanf("%d%d%d", &t, &q, &n);
	int tp;double x, y;
	REP(i, t){
		scanf("%d%lf%lf", &tp, &x, &y);
	  topic.push_back(tp);
		point[tp] = MP(x, y);
	}
	int que, qn;
	REP(i, q){
		scanf("%d%d", &que, &qn);
		REP(j, qn){
			scanf("%d", &tp);
			topicQuestion[tp].insert(que);
		}
	}
	char st[2];
	int rn;
//	out("input");
	REP(i, n){
		scanf("%s%d%lf%lf", st, &rn, &x, &y);
		if(st[0] == 't'){
			int res = 0;
				getNearTopics(x, y);
				FE(it, (*currentPoints)){ 
					if(res<rn ){
						res++;
						printf("%d ", it->second);
					} else {
						break;
					}
				}
				delete currentPoints;
		} else {
			int res = 0;
			SPD ques;
			getNearTopics(x, y);
			FE(it, (*currentPoints)){ 
				FE(iq, topicQuestion[it->second]){
						ques.insert(MP(it->first, *iq));
				}
			}
			set<int> resq;
				FE(it, ques){ 
					if(res<rn){
						if(resq.insert(it->second).second){
							res++;
							printf("%d ", it->second);
						}
					} else {
						break;
					}
				}

			delete currentPoints;
		}
		puts("");
	}
	return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */

