#include <stdio.h>
#include <stdlib.h>

int main(int argc, char* argv[]) {
    short x =30000;
    short y = 30000;
    int zz = x+y;
    printf("%d 0x%x\n", zz, zz);

    short z = x+y;
    printf("%d 0x%x\n", z, z);
    
    if (x+y < x) {
        printf("1\n");
    } else {
        printf("2\n");
    }
}
