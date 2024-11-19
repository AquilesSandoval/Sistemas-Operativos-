#include <stdio.h>

int multiplicar(int n1,int n2) {
	if(n2==0){
		return 1;
	}else{
		return  n1 + multiplicar(n1,n2-1);
	}
}

int potenciar (int n1,int n2){
	if(n2==0){
		return 1;
	}else{
		return multiplicar(n1,potenciar(n1,n2-1));
	}
}

int dividir(int n1,int n2){
	if(n1<n2){
		return 0;
	}
	else{
		return 1 + dividir(n1-n2,n2);
	}
 	
}
   
  



int main() {
	int num1,num2;
    printf("Valor del primer numero");
    scanf("%d",&num1);
    printf("Valor del segundo numero");
    scanf("%d",&num2);

	printf(" Esta es su potencia : %d", potenciar(num1,num2));
	printf(" Esta es su divicion : %d", dividir(num1,num2));
    
    
    
    return 0;
}

