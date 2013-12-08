
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
/**************** Main program *******************/



int main ( int argc, char *argv[] ) {
	//freopen("basin.cppin","r",stdin);
	int n;scanf("%d", &n);
	int a[n+1][n+1];
	for(int i = 0; i< n; ++i){
		for(int j = 0; j< n; ++j){
			scanf("%d", &a[i][j]);
		}
	}

	// 1 = top
	// 2 = bottom
	// 3 = left
	// 4 = right
	// 0 = itself
	vector<vector<int> > direction;
	vector<pair<int, int> > sink;
	for(int i = 0; i< n; ++i){
		vector<int> tmp;
		for(int j = 0; j< n; ++j){
			int min = a[i][j];
			int d = 0;
			if(i-1 >= 0 && min > a[i-1][j]){
				min = a[i-1][j];
				d = 1;
			}
			if(j-1>=0 && min > a[i][j-1]){
				min = a[i][j-1];
				d = 3;
			}
			if(i+1 < n && min > a[i+1][j]){
				min = a[i+1][j];
				d = 2;
			}
			if(j+1 < n && min > a[i][j+1]){
				min = a[i][j+1];
				d = 4;
			}
			tmp.push_back(d);
			if(d == 0){
				sink.push_back(make_pair(i, j));
			}
		}
		direction.push_back(tmp);
	}

	//bfs
	vector<int> counts;
	FE(it, sink){
		int count = 0;
		queue<pair<int, int> > q;
		q.push(*it);
		while(!q.empty()){
				pair<int, int> top = q.front();q.pop();
				count++;
				direction[top.first][top.second] = 10;
				if(top.first -1 >= 0 && direction[top.first-1][top.second] == 2){
					q.push(make_pair(top.first-1, top.second));
				}
				if(top.second -1 >= 0 && direction[top.first][top.second -1] == 4){
					q.push(make_pair(top.first, top.second-1));
				}
				if(top.first + 1 < n && direction[top.first+1][top.second] == 1){
					q.push(make_pair(top.first + 1, top.second));
				}
				if(top.second + 1 < n && direction[top.first][top.second + 1] == 3){
					q.push(make_pair(top.first, top.second + 1));
				}
		}
		counts.push_back(count);
	}

	sort(counts.begin(), counts.end(), greater<int>());
	FE(it, counts){
		printf("%d ", *it);
	}

	return EXIT_SUCCESS;
}				/* ----------  end of function main  ---------- */
