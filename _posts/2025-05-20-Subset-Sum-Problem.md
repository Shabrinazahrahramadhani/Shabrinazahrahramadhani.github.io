---
title: "SUBSET SUM PROBLEM"
date: 2025-05-20
categories: [DESAIN ANALISIS ALGORITMA, BACKTRACKING]
tags: [daa, algorithm, backtracking, subset]
---
Pengantar

Bayangkan kamu memiliki sekumpulan angka dan sebuah angka target. Apakah mungkin memilih sebagian angka dari kumpulan tersebut sehingga jumlahnya sama persis dengan target?

Itulah inti dari Subset Sum Problem – masalah klasik dalam algoritma kombinatorial dan dasar dari masalah NP-Complete.

Masalah ini sering muncul dalam konteks:
	•	Keuangan (misalnya pemilihan kombinasi koin)
	•	Kriptografi
	•	Pengemasan barang
	•	Dan berbagai masalah optimasi

Definisi Masalah

Diberikan:
	•	Sebuah array S[] berisi n bilangan bulat positif
	•	Sebuah bilangan target T

Tujuan:
	•	Apakah ada subset dari S (tidak harus berurutan) yang jumlah elemennya = T?

Pendekatan Penyelesaian

1. Backtracking (Brute-force)

Coba semua kombinasi subset → waktu: O(2^n)

2. Dynamic Programming (Tabulasi/Bottom-Up)

Gunakan tabel untuk menyimpan hasil antara.

3. Top-Down dengan Memoization

Rekursi dengan penyimpanan hasil submasalah agar tidak dihitung ulang.

Contoh Soal

S = {3, 34, 4, 12, 5, 2}
T = 9

Apakah ada subset dengan jumlah 9?

Jawaban: Ya
Karena: 4 + 3 + 2 = 9

Implementasi C++ (Dynamic Programming)

#include <iostream>
#include <vector>
using namespace std;

bool isSubsetSum(const vector<int>& set, int n, int target) {
    // Buat tabel DP[n+1][target+1]
    vector<vector<bool>> dp(n + 1, vector<bool>(target + 1, false));

    // Jika target = 0, jawabannya true (ambil kosong)
    for (int i = 0; i <= n; i++)
        dp[i][0] = true;

    // Isi tabel
    for (int i = 1; i <= n; i++) {
        for (int sum = 1; sum <= target; sum++) {
            if (set[i - 1] > sum)
                dp[i][sum] = dp[i - 1][sum];
            else
                dp[i][sum] = dp[i - 1][sum] || dp[i - 1][sum - set[i - 1]];
        }
    }

    return dp[n][target];
}

int main() {
    vector<int> set = {3, 34, 4, 12, 5, 2};
    int target = 9;

    if (isSubsetSum(set, set.size(), target))
        cout << "Terdapat subset dengan jumlah " << target << endl;
    else
        cout << "Tidak ditemukan subset dengan jumlah " << target << endl;

    return 0;
}

Analisis Kompleksitas

Aspek	Nilai
Waktu	O(n * target)
Ruang	O(n * target)

Bisa dioptimasi ke O(target) ruang menggunakan 1D array.

Visualisasi Tabel DP

Contoh:
	•	Baris = elemen array (dari 0 hingga n)
	•	Kolom = kemungkinan jumlah (0 hingga target)

Tiap sel dp[i][j] bernilai true jika ada subset dari elemen 0..i-1 yang jumlahnya j.
