struct notAlign4{
    char bla;
};

void printa(){
    print_s((char*)"cyka");
}

void main(){
    struct notAlign4 na;
    printa();
}