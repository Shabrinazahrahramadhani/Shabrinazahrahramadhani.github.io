---
title: "N-QUEENS PROBLEM"
date: 2025-05-20
categories: [DESAIN ANALISIS ALGORITMA, BACKTRACKING]
tags: [daa, algorithm, backtracking, nqueens]
---
Pengantar

Bayangkan kamu memiliki papan catur berukuran N x N. Tugasmu adalah menempatkan N ratu (queen) di papan tersebut, satu di tiap baris, sehingga tidak ada dua ratu yang saling menyerang.

Masalah ini disebut: N-Queens Problem

Masalah ini adalah salah satu contoh klasik dalam algoritma pencarian dan backtracking, dan digunakan untuk melatih kemampuan logika, rekursi, dan strategi pemecahan masalah.

Konsep Dasar N-Queens

♟ Aturan Ratu (Queen) di Catur:
	•	Bisa bergerak horizontal, vertikal, dan diagonal.

Diberikan:
	•	Papan berukuran N x N

Tujuan:
	•	Tempatkan N ratu, satu per baris, sehingga:
	•	Tidak ada dua ratu yang berada di:
	•	Kolom yang sama
	•	Diagonal utama yang sama
	•	Diagonal sekunder yang sama

Pendekatan: Backtracking

Backtracking = mencoba solusi sebagian, dan jika tidak memungkinkan, mundur (backtrack) ke langkah sebelumnya.

Langkah-langkah:
	1.	Mulai dari baris pertama.
	2.	Di setiap baris, coba tempatkan ratu di setiap kolom satu per satu.
	3.	Untuk tiap posisi yang dicoba:
	•	Cek apakah aman (tidak ada ratu lain menyerang).
	•	Jika aman, lanjut ke baris berikutnya secara rekursif.
	•	Jika tidak bisa lanjut → kembali ke langkah sebelumnya (backtrack).
	4.	Ulangi sampai seluruh ratu ditempatkan atau semua kemungkinan dicoba.

Contoh N = 4

Tujuan: Tempatkan 4 ratu di papan 4x4

Salah satu solusi:

[0, 1, 2, 3] = kolom
 Q . . .     → baris 0, kolom 1
 . . . Q     → baris 1, kolom 3
 . Q . .     → baris 2, kolom 0
 . . Q .     → baris 3, kolom 2

Visual:

. Q . .
. . . Q
Q . . .
. . Q .

Implementasi C++

#include <iostream>
#include <vector>
using namespace std;

bool isSafe(vector<string>& board, int row, int col, int n) {
    // Cek kolom
    for (int i = 0; i < row; i++)
        if (board[i][col] == 'Q') return false;

    // Diagonal kiri atas
    for (int i = row-1, j = col-1; i >=0 && j >=0; i--, j--)
        if (board[i][j] == 'Q') return false;

    // Diagonal kanan atas
    for (int i = row-1, j = col+1; i >=0 && j < n; i--, j++)
        if (board[i][j] == 'Q') return false;

    return true;
}

void solve(int row, vector<string>& board, int n, vector<vector<string>>& solutions) {
    if (row == n) {
        solutions.push_back(board);
        return;
    }

    for (int col = 0; col < n; col++) {
        if (isSafe(board, row, col, n)) {
            board[row][col] = 'Q';
            solve(row + 1, board, n, solutions);
            board[row][col] = '.'; // backtrack
        }
    }
}

vector<vector<string>> solveNQueens(int n) {
    vector<vector<string>> solutions;
    vector<string> board(n, string(n, '.'));
    solve(0, board, n, solutions);
    return solutions;
}

int main() {
    int n = 4;
    auto solutions = solveNQueens(n);
    cout << "Jumlah solusi: " << solutions.size() << endl;
    for (auto& sol : solutions) {
        for (string row : sol) {
            cout << row << endl;
        }
        cout << endl;
    }
    return 0;
}

 Analisis Kompleksitas

Aspek	Nilai
Waktu (Worst)	O(N!)
Ruang	O(N^2) (untuk papan)
Jumlah solusi	Tergantung nilai N

