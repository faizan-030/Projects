#include<iostream>
#include<vector>
using namespace std;


#define inf 99
#define vt 7
int graph[vt][vt] = {
        {0, 2, 1, 2, 0, 0, 0},
        {2, 0, 1, 0, 2, 0, 0},
        {1, 1, 0, 1, 0, 2, 0},
        {2, 0, 1, 0, 0, 0, 2},
        {0, 2, 0, 0, 0, 1, 0},
        {0, 0, 2, 0, 1, 0, 1},
        {0, 0, 0, 2, 0, 1, 0},
    };
string locations[vt] = {"Restaurant","G6","G7","G8","F6","F7","F8"};



struct menu{
    string food;
    int price;
    int count = 10;
    menu*next = NULL;
};
menu*menufirst = NULL;
menu*menulast = NULL;


struct order{
    int tableNo;
    string totalorder;
    int totalprice;
    order*next = NULL;
};
order*orderfront = NULL;
order*orderrear = NULL;


struct table
{
    int capacity;
    bool occupied;
    int row;
    int capacity;
    table *left;
    table *right;
};
table *tableroot = NULL;
int count = 0;

struct total{
    int orderNo;
    int price;
    total *next = NULL;
};
int totalcount = 0;
total* totalirst = NULL;

void insert_Menu(string name,int cost){

    menu * newNode = new menu;
    newNode->food = name;
    newNode->price = cost;
    if (menufirst == NULL)
    {
        menufirst = menulast = newNode;
    }
    else{
    menulast->next = newNode;
    menulast = newNode;
    }
}
void display_Menu(){
    if (menufirst == NULL)
    {
        cout<<"menu is empty"<<endl;
        return;
    }
    menu*p = menufirst;
    cout<<"NAME"<<" ---- "<<"PRICE"<<endl;
    while (p != NULL)
    {
        cout<<p->food<<" ---- "<<p->price<<endl;
        p = p->next;
    }
}

void generatMenu(){
    
    string n1 = "Biryani";
    int p1 = 200;
    insert_Menu(n1,p1);
    string n2 = "Karahi";
    int p2 = 1600;
    insert_Menu(n2,p2);
    string n3 = "Bar-b-que";
    int p3 = 350;
    insert_Menu(n3,p3);
    string n4= "Nihari";
    int p4 = 800;
    insert_Menu(n4,p4);
    string n5 = "Naan";
    int p5 = 40;
    insert_Menu(n5,p5);
    
}
void pushTotal(int price){
    total * newNode = new total;
    totalcount++;
    newNode->orderNo = totalcount;
    newNode->price = price;
    if (totalirst == NULL)
    {
        totalirst = newNode;
    }
    else
    {
        newNode->next = totalirst;
        totalirst = newNode;
    }
}
void displayTotal(){
    if (totalirst == NULL)
    {
        cout<<"no orders today"<<endl;
        return;
    }
    else
    {
        total * temp = totalirst;
        while (temp != NULL)
        {
            cout<<"Order Number : "<<temp->orderNo<<" Price : "<<temp->price<<endl;
            temp = temp->next;
        }
    }
}
void freeOrderList(){
    total *p;
    if (totalirst== NULL)
    {
        cout<<"no orders today"<<endl;
    }
    else{
     while (totalirst != NULL)
     {
        p = totalirst;
        totalirst = totalirst->next;
        free (p);
     }
     cout<<"today's all orders clear"<<endl;
    }
}


string find_food(string name){
    if (menufirst == NULL)
    {
        cout<<"menu is empty"<<endl;
        return "";
    }
    menu*p = menufirst;
    while (p != NULL && p->food != name)
    {
        p = p->next;
    }
    if (p == NULL)
    {
        cout<<name<<" is not available"<<endl;
        return "";
    }
    else{
        p->count = p->count-1;
        return p->food;
    }
}
int find_price(string name){
    if (menufirst == NULL)
    {
        cout<<"menu is empty"<<endl;
        return 0;
    }
    menu*p = menufirst;
    while (p != NULL && p->food != name)
    {
        p = p->next;
    }
    if (p == NULL)
    {
        return 0;
    }
    else{
        return p->price;
    }
}
table *addnewTable(int key,int c){
    table* temp = new table;
    temp->occupied = false;
    temp->capacity = key;
    temp->capacity = c;
    temp->left = NULL;
    temp->right = NULL;
    temp->row = 1;
    return temp;
}
int getrowOfTable(table* n) {
    if (n == NULL) {
        return -1;
    }
    return n->row;
}
int getBalanceFactor(table* n) {
    if (n == NULL) {
        return 0;
    }
    return getrowOfTable(n->left) - getrowOfTable(n->right);
}
table* rightRotate(table* y) {
    table* x = y->left;
    table* T2 = x->right;

    x->right = y;
    y->left = T2;

    y->row = 1 + max(getrowOfTable(y->right), getrowOfTable(y->left));
    x->row = 1 + max(getrowOfTable(x->right), getrowOfTable(x->left));

    return x;
}
table* leftRotate(table* x) {
    table* y = x->right;
    table* T2 = y->left;

    y->left = x;
    x->right = T2;

    y->row = 1 +max(getrowOfTable(y->right), getrowOfTable(y->left));
    x->row = 1 +max(getrowOfTable(x->right), getrowOfTable(x->left));

    return y;
}
table* insert(table* node, int key,int c) {
    if (node == NULL) {
        return addnewTable(key,c);
    }

    if (key < node->capacity) {
        node->left = insert(node->left, key);
    } else if (key > node->capacity) {
        node->right = insert(node->right, key);
    } else {
        return node;
    }

    node->row = 1 + max(getrowOfTable(node->left), getrowOfTable(node->right));
    int balanceFactor = getBalanceFactor(node);

    if (balanceFactor > 1 && key < node->left->capacity) {
        return rightRotate(node);
    }

    if (balanceFactor < -1 && key > node->right->capacity) {
        return leftRotate(node);
    }
    if (balanceFactor > 1 && key > node->left->capacity) {
        node->left = leftRotate(node->left);
        return rightRotate(node);
    }

    if (balanceFactor < -1 && key < node->right->capacity) {
        node->right = rightRotate(node->right);
        return leftRotate(node);
    }

    return node;
}
void preorder_traversal(table* root) {
    if (root != nullptr) {
        
        cout <<"Capacity "<< root->capacity <<"  Table NO"<<root->capacity<<"  Occupied"<<root->occupied<<endl;
        preorder_traversal(root->left);
        preorder_traversal(root->right);
        
    }
}
table* checkTabel(table* node, int key) {
    if (node == NULL || node->capacity == key) {
        return node;
    }

    if (key < node->capacity) {
        return checkTabel(node->left, key);
    } else {
        return checkTabel(node->right, key);
    }
}
order*gettableNo(int key){
    order*temp = orderfront;
    while (temp != NULL && temp->tableNo != key)
    {
        temp = temp->next;
    }
    if (temp == NULL)
    {
        cout<<"table not found"<<endl;
    }
    else{
        return temp;
    }
}

void take_Order(int table){
    order *newOrder= new order;
   
    int totalPriceOfOrder = 0;
    string totalOrderGiven;
    newOrder->tableNo = table;
    string order;
    cout<<"enter the food customer wants"<<endl;
    cin>>order;
    while (order != "end" )
    {
        totalOrderGiven = totalOrderGiven+","+(find_food(order));
        totalPriceOfOrder = totalPriceOfOrder + find_price(order);
        cout<<"enter the food customer wants"<<endl;
        cin>>order;
    }
    newOrder->totalprice = totalPriceOfOrder;
    pushTotal(newOrder->totalprice);
    totalOrderGiven = totalOrderGiven+",end";
    newOrder->totalorder = totalOrderGiven;
    if (orderfront == NULL)
    {
        orderfront = orderrear = newOrder;
    }
    else{
        orderrear->next = newOrder;
        orderrear = newOrder;
    }
    
}
int minDistance(int dist[vt], bool tarr[]) {
    int min = 9999999;
    int ind;

    for (int i = 0; i < vt; i++) {
        if (tarr[i] == false && dist[i] <= min) {
            min = dist[i];
            ind = i;
        }
    }
    return ind;
}

void printPath(int currentVertex, vector<int>& parents) {
    if (currentVertex == -1) {
        return;
    }
    printPath(parents[currentVertex], parents);
    if (currentVertex == 0) {
        cout << "";
    } else {
        cout << locations[currentVertex] << " ";
    }
}

void find_shortest_path(string n) {
    bool tarr[vt];
    int newdist[vt];
    vector<int> parents(vt);

    for (int i = 0; i < vt; i++) {
        newdist[i] = inf;
        tarr[i] = false;
        parents[i] = -1;
    }

    newdist[0] = 0;

    for (int i = 0; i < vt; i++) {
        int m = minDistance(newdist, tarr);
        tarr[m] = true;

        for (int k = 0; k < vt; k++) {
            if (!tarr[k] && graph[m][k] > 0 && newdist[m] + graph[m][k] < newdist[k]) {
                parents[k] = m;
                newdist[k] = newdist[m] + graph[m][k];
            }
        }
    }
    cout << "Desired Sector\tShortet Distance\tpath" << endl;
    for (int i = 0; i < vt; i++) {
		if(locations[i] == n){
        cout << locations[i]<< "\t\t";
        cout << newdist[i] << "\t\t\t";
        printPath(i, parents);
        cout << endl;
		}
    }
}
void delivery_Order(string sector){
     order *newOrder= new order;
   
    int totalPriceOfOrder = 0;
    string totalOrderGiven;
    
    newOrder->tableNo = 0;
    string order;
    cout<<"enter the food customer wants"<<endl;
    cin>>order;
    while (order != "end" )
    {
        totalOrderGiven = totalOrderGiven+","+(find_food(order));
        totalPriceOfOrder = totalPriceOfOrder + find_price(order);
        cout<<"enter the food customer wants"<<endl;
        cin>>order;
    }
    newOrder->totalprice = totalPriceOfOrder;
    pushTotal(newOrder->totalprice);
    totalOrderGiven = totalOrderGiven+",end";
    newOrder->totalorder = totalOrderGiven;
    if (orderfront == NULL)
    {
        orderfront = orderrear = newOrder;
    }
    else{
        orderrear->next = newOrder;
        orderrear = newOrder;
    }
    cout<<"The shortest path To reach "<<sector<<"is :"<<endl;
    find_shortest_path(sector);
}
void display_Order(){
    if (orderfront == NULL)
    {
        cout<<"no orders has been taken"<<endl;
        return;
    }
    int t;
    cout<<"which table order you want to dispaly"<<endl;
    cin>>t;
    order *temp = gettableNo(t);

    cout<<"NAME"<<endl;
    string disordere;
    int disprice;
    temp->totalorder.erase(0,1);
    while (temp->totalorder != "end")
    {
        int pos = temp->totalorder.find(',');
        disordere = temp->totalorder.substr(0,pos);
        disprice = find_price(disordere);
        temp->totalorder.erase(0,pos);
        cout<<disordere<<" ---- "<<disprice<<endl;
        temp->totalorder.erase(0,1);
    }
    cout<<"TOTAL BILL"<<endl;
    cout<<temp->totalprice<<endl;
    
}
void servefood(){
    if (orderfront == NULL)
    {
        cout<<"order has not been taken"<<endl;
        return;
    }
    else{
        if (orderfront == orderrear)
        {
            order*temp = orderfront;
            orderfront = orderrear = NULL;
            delete temp;
        }
        else{
            order*temp = orderfront;
            orderfront = temp->next;
            delete temp;
        }
        cout<<"the order has been serverd"<<endl;
    }
}


#define MAX 100

int reservation[MAX];
int n = 0;

void makeReservation(int & n, int persons) {
    // Step 1: Add the new value and set its POS
    n = n + 1;
    int pos = n;
    reservation[n] = persons;
    while (pos > 1) {
        int par = pos / 2;
        if (reservation[pos] <= reservation[par]) {
            return;
        } else if (reservation[pos] > reservation[par]) {
            int temp = reservation[pos];
            reservation[pos] = reservation[par];
            reservation[par] = temp;
            pos = par;
        }
    }
}
void deleteReservation(int heap[], int& n) {
    int last = heap[n - 1];
    n = n - 1;

    int ptr = 1, left = 2, right = 3;

    heap[ptr] = last;

    while (left <= n) {
        if (right <= n) {
            if (heap[ptr] < heap[left] || heap[ptr] < heap[right]) {

                if (heap[right] >= heap[left]) {
                    swap(heap[ptr], heap[right]);
                    ptr = right;
                } else {
                    swap(heap[ptr], heap[left]);
                    ptr = left;
                }
            } else {
                break;
            }
 } else if (heap[ptr] < heap[left]) {
            swap(heap[ptr], heap[left]);
            ptr = left;
        } else {
            
            break;
        }
        
        left = 2 * ptr;
        right = left + 1;
    }
    cout<<"Reservation Deleted Sucessfully"<<endl;
}


void displayReservations(int &n){
    
    for (int i = 1; i <= n ; i++)
    {
        cout<<"reservation Of Age :"<<reservation[i]<<endl;
    }
    cout<<endl;
}

void orderManagement() {
    int choiceForOrderManagement;

    do {
        cout << "PRESS 1 TO TAKE ORDER" <<endl;
        cout << "PRESS 2 TO DISPLAY ORDER" << endl;
        cout << "PRESS 3 TO SERVE FOOD" <<endl;
        cout << "PRESS 0 TO EXIT ORDER MANAGEMENT" <<endl;
        cin >> choiceForOrderManagement;

        switch (choiceForOrderManagement) {
            case 1:{
                cout<<"Enter 1 if you want home delivery :"<<endl;
                int dil;
                cin>>dil;
                if (dil == 1)
                {
                cout<<"Enter your sector : "<<endl;
                string sec;
                cin>>sec;
                delivery_Order(sec);
                break;

                }else
                {
                    int tableNo;
                    cout<<"enter table Number"<<endl;
                    cin>>tableNo;
                    
                    table *check = checkTabel(tableroot,tableNo);
                   
                    
                        if (check != NULL)
                        {
                            take_Order(tableNo);
                            break;
                        }else{
                            cout<<"table is not present\nPlease enter valid table number"<<endl;
                            cout<<endl;
                            cout<<"The present Table are :"<<endl;
                            preorder_traversal(tableroot);
                            
                        }
                    
                break;
                }
            }
            case 2:{
                display_Order();
                break;
            }
            case 3:{
                servefood();
                break;
            }
            case 0:
                break;
            default:
                cout << "PRESS A VALID BUTTON BETWEEN 0 - 3" <<endl;
        }

    } while (choiceForOrderManagement != 0);
}

void staffManagement(){
    int choice;
    do
    {
        cout << "PRESS 1 TO ADD NEW DISH" << endl;
        cout << "PRESS 2 TO ADD NEW TABLE" << endl;
        cout << "PRESS 3 TO DISPLAY TOTAL ORDERS TODAY" << endl;
        cout << "PRESS 0 TO EXIT" << endl;
        cin>>choice;

        switch (choice) {
        case 1: {
            string foodName;
            int price;
            cout << "Enter name of food: " << endl;
            cin >> foodName;
            cout << "Enter its price: " << endl;
            cin >> price;
            insert_Menu(foodName, price);
            break;
        }
        case 2: {
            int tableNumber;
            cout << "Enter table number: " << endl;
            cin >> tableNumber;
            tableroot = insert(tableroot,tableNumber);
            int takeOrder;
            cout<<"enter 1 to take order at table"<<endl;
            cin>>takeOrder;
            if (takeOrder == 1)
            {
                take_Order(tableNumber);
            }
            
            break;
        }
        case 3:
            displayTotal();
            break;
        case 0:
            break;
        default:
            cout << "PRESS VALID BUTTON BETWEEN 0 - 3" << endl;
            break;
    }
} while (choice != 0);

    
}
void reservationManagement(){
    int choice;
    do
    {
        cout<<"PRESS 1  TO MAKE RESERVATION"<<endl;
        cout<<"PRESS 2 TO SHOW RESERVATION LIST"<<endl;
        cout<<"PRESS 3 TO DELETE RESERVATION"<<endl;
        cout<<"PRESS 0 TO EXIT RESERVATION MANAGEMENT"<<endl;
    
        cin>>choice;

        switch (choice) {
        case 1: {
            int ageOfCustomer;
            cout<<"What is age of Customer"<<endl;
            cin>>ageOfCustomer;
            makeReservation(n,ageOfCustomer);
            break;
        }
        case 2: {
            displayReservations(n);
            break;
        }
        case 3:
            deleteReservation(reservation,n);
            break;
        case 0:
            break;
        default:
            cout << "PRESS VALID BUTTON BETWEEN 0 - 3" << endl;
            break;
    }
} while (choice != 0);

    
}


int main(){
    generatMenu();
    int choice;
do {
    cout << "PRESS 1  FOR MENU MANAGEMENT" << endl;
    cout << "PRESS 2 FOR ORDER MANAGEMENT" << endl;
    cout << "PRESS 3 FOR RESERVATION MANAGEMENT" << endl;
    cout << "PRESS 4 FOR STAFF MANAGEMENT" << endl;
    cout << "PRESS 0 TO EXIT" << endl;

    cin >> choice;

    switch (choice) {
        case 1:
            display_Menu();
            break;
        case 2:
            orderManagement();
            break;
        case 3:
            reservationManagement();
            break;
        case 4:
            staffManagement();
            break;
        case 0:
            cout << "Exiting..." << endl;
            break;
        default:
            cout << "PRESS VALID BUTTON BETWEEN 0 - 5" << endl;
            break;
    }
} while (choice != 0);

return 0;

}





