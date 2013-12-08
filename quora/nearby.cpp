
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
#define PI      pair< double,double >//::PI
#define MP      make_pair//::MP
#define pi      3.1415926535897932384626433832795//::pi
#define INF 	2000000000//::INF

typedef long long LL;//::LL
typedef unsigned long long ULL;//::ULL
typedef long double LD;//::LD

#define sqr(x) (x)*(x)

/* Only for Debugging */
#define out(__debug) cout << #__debug<< "=" <<__debug << endl;//::out(
#define outC(A) cout<<"{"; FE(it,A)cout << *it << " " ;cout<<"}"<<endl;//::outContainer
template<class T>inline void outA(T A[], int n) {cout<<"{"; REP (i, n)cout<<A[i]<<" "; cout<<"}"<<endl;}//:outArray

/**************** Main program *******************/

const double eps = 0.001;

struct Comparator {
    bool operator() (const pair<double, int> &a, const pair<double, int> &b) const{
			if(abs(a.first - b.first) <= eps){
				return b.second < a.second;
			} else {
				return a.first < b.first;
			}
    }
};


pair<double, double> point[100005];
VI topic;
set<int, greater<int> > topicQuestion[100005];

//test 4
//int gridSize = 2005;
//int dividend = 500;

//except 4
int gridSize = 337;
int dividend = 3000;

vector<int> grid[2005][2005];

#define SPD set<pair<double, int>, Comparator >  

set<pair<double, int>, Comparator > *nextPoints;
set<pair<double, int>, Comparator > *currentPoints;

int getNearTopics(double x, double y, int g){

	int cX = min((int)x/dividend, gridSize);
	int cY = min((int)y/dividend, gridSize);

	// find current grid points which will be near points
	if( g == 0){
		currentPoints = new SPD();
		vector<int> &firstPoints = grid[cX][cY];
		for(int t = 0, _n = firstPoints.size(); t<_n;++t){
			pair<double, double> &p = point[firstPoints[t]];
			double dis = sqrt(sqr(p.first - x) + sqr(p.second - y));
			currentPoints->insert(MP(dis, firstPoints[t]));
		}
	} else {
		currentPoints = nextPoints;
	}

	nextPoints = new SPD();

	double maxDis = (currentPoints == NULL || currentPoints->empty()) ? 0: (*(currentPoints->rbegin())).first;
  
	int maxG = g + (maxDis / dividend) + 1;

	vector<int> eligible;
	for(int gPosition = g + 1; gPosition <= maxG; gPosition++){
		//top
		if(cY - gPosition >= 0){
			for(int i = -gPosition; i<gPosition; ++i){
				if(cX + i >=0 && cX + i < gridSize){
					vector<int> &toProcess = grid[cX+i][cY-gPosition];
					for(int j = 0, _n=toProcess.size(); j<_n; ++j){
						eligible.push_back(toProcess[j]);
					}
				}
			}
		}
		//right
		if(cX + gPosition < gridSize){
			for(int i = -gPosition; i<gPosition; ++i){
				if(cY + i >=0 && cY + i < gridSize){
					vector<int> &toProcess = grid[cX + gPosition][cY + i];
					for(int j = 0, _n=toProcess.size(); j<_n; ++j){
						eligible.push_back(toProcess[j]);
					}
				}
			}
		}
		//bottom
		if(cY + gPosition < gridSize){
			for(int i = -gPosition; i<gPosition; ++i){
				if(cX - i >=0 && cX - i < gridSize){
					vector<int> &toProcess = grid[cX - i][cY + gPosition];
					for(int j = 0, _n=toProcess.size(); j<_n; ++j){
						eligible.push_back(toProcess[j]);
					}
				}
			}
		}
			//left
		if(cX - gPosition >= 0){
			for(int i = -gPosition; i<gPosition; ++i){
				if(cY - i >=0 && cY - i < gridSize){
					vector<int> &toProcess = grid[cX - gPosition][cY - i];
					for(int j = 0, _n=toProcess.size(); j<_n; ++j){
						eligible.push_back(toProcess[j]);
					}
				}
			}
		}
	}
	for(int i = 0, _n=eligible.size(); i<_n;++i){
		pair<double, double> &p = point[eligible[i]];
		double dis = sqrt(sqr(p.first - x) + sqr(p.second - y));
		if(abs(maxDis - dis) <= eps || maxDis > dis){
			currentPoints->insert(MP(dis, eligible[i]));
		} else {
			nextPoints->insert(MP(dis, eligible[i]));
		}
	}

	return maxG;
}


int main ( int argc, char *argv[] ) {
//	freopen("nearby.cppin","r",stdin);
	//freopen("nearby.cppout","w",stdout);
	int t, q, n;
	scanf("%d%d%d", &t, &q, &n);
	int tp;double x, y;
	double mx = 0;
	REP(i, t){
		scanf("%d%lf%lf", &tp, &x, &y);
	  topic.push_back(tp);
		point[tp] = MP(x, y);
		if(mx < x){
			mx = x;
		}
		if(mx < y){
			mx = y;
		}
		//grid[(int)x/dividend][(int)y/dividend].push_back(tp);
	}

	dividend = 4.0/2 * (sqrt(mx)) + 50;
	gridSize = mx / dividend + 5;

	REP(i, t){
		pair<double, double> &p = point[topic[i]];
		grid[(int)p.first/dividend][(int)p.second/dividend].push_back(topic[i]);
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
			int next = 0;
			while(res < rn && next < gridSize - 3){
				next = getNearTopics(x, y, next);
				FE(it, (*currentPoints)){ 
					if(res<rn ){
						res++;
						printf("%d ", it->second);
					} else {
						break;
					}
				}
				//cout<<(*currentPoints).size()<<":"<<res<<":"<<rn<<endl;
				delete currentPoints;
			}
		} else {
			int res = 0;
			int next = 0;
			int numberOfTopics = 0;
			set<int> resq;
			while(res < q && res < rn && next < gridSize - 3 && numberOfTopics < t){
				SPD ques;
				next = getNearTopics(x, y, next);
				numberOfTopics += (*currentPoints).size();
				FE(it, (*currentPoints)){ 
					FE(iq, topicQuestion[it->second]){
						ques.insert(MP(it->first, *iq));
					}
				}
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
		}
		puts("");
	}
	return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */
