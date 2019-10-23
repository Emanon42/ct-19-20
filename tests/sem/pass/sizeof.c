struct bla{
	int s;
	char* f;
};

void main() {
	int a;
	a = sizeof(int)+sizeof(int*)+sizeof(struct bla)+sizeof(struct bla*);
}
