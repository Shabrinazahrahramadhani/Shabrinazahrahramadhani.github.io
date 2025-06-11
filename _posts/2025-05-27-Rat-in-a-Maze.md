---
title: RAT IN MAZE
date: 27-05-2025
categories: [DESAIN ANALISIS ALGORITMA, BACKTRACKING]
tags: [daa, algorithm, backtracking]
---
Pengantar

Bayangkan seekor tikus berada di sudut kiri atas dari sebuah labirin berbentuk matriks, dan ia ingin mencapai sudut kanan bawah. Namun, ia hanya bisa berjalan ke bawah atau kanan, dan hanya melalui jalur yang tidak terhalang (nilai 1).

Tujuan: Temukan semua jalur dari awal ke tujuan dalam bentuk urutan langkah.

Masalah ini merupakan contoh klasik dari Backtracking dan sering digunakan untuk memahami penjelajahan jalur dalam graf atau grid.

Definisi Masalah

Diberikan:
	•	Matriks N x N bernama maze dengan nilai:
	•	1 = jalan bisa dilalui
	•	0 = jalan terhalang

Tujuan:
	•	Cari semua jalur dari (0,0) ke (N-1,N-1) menggunakan hanya langkah: kanan (R), bawah (D), kiri (L), dan atas (U)
	•	Tikus tidak boleh melewati sel lebih dari sekali

Pendekatan: Backtracking

Strategi:
	1.	Mulai dari posisi (0,0)
	2.	Dari setiap titik, coba ke 4 arah (D, R, U, L)
	3.	Pastikan:
	•	Posisi masih di dalam maze
	•	Belum pernah dikunjungi
	•	Bukan dinding (0)
	4.	Tandai posisi sebagai dikunjungi
	5.	Jika sampai (N-1,N-1), simpan jalurnya
	6.	Kembali (backtrack), lalu coba arah lain

Contoh Maze

maze = { {1, 0, 0, 0},
         {1, 1, 0, 1},
         {0, 1, 0, 0},
         {1, 1, 1, 1} }

N = 4

Jalur yang valid (contoh):
	•	DDRDRR

Implementasi C++

#include <iostream>
#include <vector>
#include <string>
using namespace std;

bool isSafe(int x, int y, vector<vector<int>>& maze, vector<vector<bool>>& visited, int n) {
    return x >= 0 && x < n && y >= 0 && y < n &&
           maze[x][y] == 1 && !visited[x][y];
}

void solveMaze(vector<vector<int>>& maze, int x, int y, int n,
               vector<vector<bool>>& visited, string path, vector<string>& result) {
    if (x == n-1 && y == n-1) {
        result.push_back(path);
        return;
    }

    // 4 arah: D, L, R, U
    int dx[] = {1, 0, 0, -1};
    int dy[] = {0, -1, 1, 0};
    char dir[] = {'D', 'L', 'R', 'U'};

    for (int i = 0; i < 4; i++) {
        int newX = x + dx[i];
        int newY = y + dy[i];

        if (isSafe(newX, newY, maze, visited, n)) {
            visited[x][y] = true;
            solveMaze(maze, newX, newY, n, visited, path + dir[i], result);
            visited[x][y] = false; // backtrack
        }
    }
}

vector<string> findPaths(vector<vector<int>>& maze, int n) {
    vector<string> result;
    vector<vector<bool>> visited(n, vector<bool>(n, false));
    if (maze[0][0] == 1)
        solveMaze(maze, 0, 0, n, visited, "", result);
    return result;
}

int main() {
    vector<vector<int>> maze = { {1, 0, 0, 0},
                                 {1, 1, 0, 1},
                                 {0, 1, 0, 0},
                                 {1, 1, 1, 1} };

    int n = maze.size();
    vector<string> paths = findPaths(maze, n);

    if (paths.empty())
        cout << "Tidak ada jalur yang ditemukan." << endl;
    else {
        cout << "Jalur yang ditemukan:\n";
        for (string path : paths)
            cout << path << endl;
    }

    return 0;
}

Analisis Kompleksitas

Aspek	Nilai
Waktu (Worst)	O(4^(N*N)) (karena 4 arah dari tiap sel)
Ruang	O(N*N) untuk matriks kunjungan dan rekursi

Visualisasi
	•	Gunakan matriks 4x4
	•	Tampilkan langkah-langkah tikus secara berurutan:
	•	Tandai titik yang dikunjungi
	•	Garis panah arah
	•	Tandai solusi dengan jalur huruf: DDRDRR

