/*
 * private-clause.c
 *
 *  Created on: 02/04/2014
 *      Author: Carlos de la Torre
 */
#include <stdio.h>
#ifdef _OPENMP
	#include <omp.h>
#else
	#define omp_get_thread_num() 0
#endif
int main() {
	int i, n = 7;
	int a[n], suma=0;
	for (i = 0; i < n; i++)
		a[i] = i;
	#pragma omp parallel private(suma)
	{
	suma = 0;
	#pragma omp for
		for (i = 0; i < n; i++) {
			suma += a[i];
			printf("thread %d suma a[%d] / ", omp_get_thread_num(), i);
		}
		printf("\n* thread %d suma= %d", omp_get_thread_num(), suma);
	}
	printf("\nSuma final: %d\n",suma);
return 0;
}
