
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
class compare { // simple comparison function
   public:
      bool operator()(const char* x,const char* y) {
				return strcmp(x, y) < 0;
			}
};

class Node {
	private:
             char *key;
             char *value;
             Node *next;
             Node *prev;
	public:
             Node(char* key, char* value) {
                this->key = key;
                this->value = value;
                next = NULL;
                prev = NULL;
            }

             Node * getPrev() {
                return prev;
            }

             void setPrev(Node * prev) {
                this->prev = prev;
            }

             Node * getNext() {
                return next;
            }

             void setNext(Node * next) {
                this->next = next;
            }

            char* getValue() {
                return value;
            }

             void setValue(char* value) {
                this->value = value;
            }

            char* getKey() {
                return key;
            }

             void setKey(char* key) {
                this->key = key;
            }
        };

   class LRUCache {
			public:  
			  Node *head;
        Node *tail;
        map<char*, Node*, compare> cache;
        int maxSize;
        int currentSize;

        void dump() {
            FE(it, cache) {
							if(it->second != NULL){
								printf("%s %s\n", it->first, it->second->getValue());
							}
            }
        }

        LRUCache() {
            head = tail = NULL;
            maxSize = 0;
            currentSize = 0;
        }
        
       void set(char* key, char* value) {
            Node * node = getNode(key);
            if (node == NULL) {
                node = new Node(key, value);
                insertNew(node);
                cache[key] = node;
            } else {
                node->setValue(value);
            }
        }

        void insertNew(Node * node) {
            if (this->head == NULL) {
                this->head = this->tail = node;
            } else {
                node->setNext(this->head);
                this->head->setPrev(node);
                this->head = node;
            }
            currentSize++;
            balance();
        }

        void balance() {
            while (currentSize > maxSize) {
                cache.erase(this->tail->getKey());
                this->tail = this->tail->getPrev();
                --currentSize;
            }
            if (this->tail == NULL) {
                this->head = this->tail;
            } else if (this->tail->getNext() != NULL) {
                Node *tmp = this->tail->getNext();
                this->tail->setNext(NULL);
                tmp->setPrev(NULL);
            }
        }

//
//        private void balance() {
//            while (currentSize > maxSize) {
//
//                cache.remove(this.tail.getKey());
//                Node<T, V> tmp = this.tail;
//                this.tail = this.tail.getPrev();
//                if (this.tail == null) {
//                    this.head = this.tail;
//                } else {
//                    this.tail.setNext(null);
//                }
//                tmp.setPrev(null);
//                --currentSize;
//            }
//        }

        Node  * getNode(char* key) {
            Node * node = cache[key];
            if (node == NULL) {
                return NULL;
            }
            if (node->getPrev() != NULL) {
                node->getPrev()->setNext(node->getNext());
                if (node->getNext() != NULL) {
                    node->getNext()->setPrev(node->getPrev());
                } else {
                    this->tail = node->getPrev();
                }
                node->setNext(this->head);
                this->head->setPrev(node);
                this->head = node;
                node->setPrev(NULL);
            }

            return node;
        }

        char* get(char* key) {
            Node * node = getNode(key);
            if (node == NULL) {
                return NULL;
            }
            return node->getValue();
        }

       char* peek(char* key) {
            Node *node = cache[key];
            if (node == NULL) {
                return NULL;
            }
            return node->getValue();
        }

        void bound(int sz) {
            this->maxSize = sz;
            balance();
        }

    };

    /**
     * @param args
     * @throws IOException
     * @throws NumberFormatException
     */
int main ( int argc, char *argv[] ) {

        int n;scanf("%d", &n);
        LRUCache lruCache;
				char command[100]; 
				int b;
        for (int i = 0; i < n; ++i) {
            scanf("%s", command);
						char * key = new char[15], * value = new char[15];
            // System.out.print(read + "#");
            // System.out.println(lruCache);
                if(strcmp("BOUND", command) == 0){
									scanf("%d", &b);
                  lruCache.bound(b);
                } else if(strcmp("SET",  command) == 0){
									scanf("%s %s", key,  value);
                  lruCache.set(key, value);
                } else if(strcmp("GET", command) == 0){
									scanf("%s", key);
                  char* value = lruCache.get(key);
									if(value == NULL){
										puts("NULL");
									} else {
										puts(value);
									}
                } else if(strcmp("PEEK", command) == 0){
									scanf("%s", key);
                  char* value = lruCache.peek(key);
									if(value == NULL){
										puts("NULL");
									} else {
										puts(value);
									}
								} else if(strcmp("DUMP", command)== 0){
                    lruCache.dump();
								}
         }
return 0;
}/* ----------  end of function main  ---------- */
