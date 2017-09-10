#include <iostream>  
#include <deque>   
#include<stdio.h>
#include<fstream>
using namespace std;
const int SIZE=9; 
int num=0;
int n;        

struct Pos {  
    Pos(int x, int y) : row(x), col(y) {};  
    int row;  
    int col;  
};
typedef deque <Pos> qpos;  
qpos Q;      
int map[SIZE][SIZE] = {  
    {1, 0, 0, 0, 0, 0, 0, 0, 0},  
    {0, 0, 0, 0, 0, 0, 0, 0, 0},  
    {0, 8, 0, 0, 0, 0, 0, 0, 0},  
    {0, 0, 0, 0, 0, 0, 0, 0, 0},  
    {0, 0, 0, 0, 0, 0, 0, 0, 0},  
    {0, 0, 0, 0, 0, 0, 0, 0, 0},  
    {0, 0, 0, 0, 0, 0, 0, 0, 0},  
    {0, 0, 0, 0, 0, 0, 0, 0, 0},  
    {0, 0, 0, 0, 0, 0, 0, 0, 0},  
};   
void printSudoku()  
{
	int x,y;
    for(x=0;x<9;++x)
    {
        for(y=0;y<9;++y)
        {
           cout << map[x][y] <<' ';
        }
        cout << endl;
    }
    cout<<endl;
}  
  
bool isValid(Pos p, int n)  
{  
    int curRow = p.row;  //��֤�С��� 
    int curCol = p.col;  
    for (int i = 0; i < 9; i++) {  
        if (map[i][curCol]==n || map[curRow][i]==n) {  
            return false;  
        }  
    }   
    int m = curRow / 3 * 3;  //��֤�Ź��� 
    int r = curCol / 3 * 3;  
    for(int j = m; j < m + 3;++j){  
        for(int k = r; k < r+3; ++k){  
            if(map[j][k]==n){  
                return false;  
            }  
        }  
    }  
  
    return true;  
}    
void solve(qpos & Q)  
{  
    
    if (Q.empty()) {  //�ݹ�������� 
    	
    	if(num<n)
    	{
    		num++;
        	printSudoku();
        	return;
		}
    }  
    Pos pos_now(Q.front().row, Q.front().col); 
    Q.pop_front();  
    for (int i = 1; i <= 9; i++) {       
        if (isValid(pos_now, i) ) {  
            map[pos_now.row][pos_now.col] = i;   // �Ϸ������������ 
            solve(Q);  
            map[pos_now.row][pos_now.col] = 0;   // ���� 
        }  
    }         
    Q.push_front(pos_now);  // �������ϣ����� 
}  
int main()  
{   
	freopen("sudoku.txt", "w" ,stdout);     
    for (int i = 0; i < 9; i++) {  
        for (int j = 0; j < 9; j++) {  
            if (0 == map[i][j]) {  
                Q.push_back(Pos(i, j));//������ֵΪ0��λ������� 
            }  
        }  
    }  
    if(!(cin>>n))
    {
    	cout<<"�������������һ��������"; 
	}
    solve(Q);
	fclose(stdout);
  
    return 0;  
}  

