---
title: BREADTH-FIRST SEARCH (BFS)
date: 2025-05-27
categories: [DESAIN ANALISIS ALGORITMA, GRAPH]
tags: [daa, algorithm, bfs, graph]
---
Pengantar

Pernahkah kamu mencari jalur terpendek dalam peta, atau ingin tahu urutan orang terhubung dalam jaringan sosial?

Itulah tempat BFS (Breadth-First Search) digunakan — algoritma eksplorasi graf yang melihat semua tetangga lebih dulu, sebelum melangkah lebih jauh.

Apa itu BFS?
	•	BFS adalah algoritma penelusuran atau traversal pada graf atau pohon.
	•	Mulai dari satu simpul (node), lalu menelusuri semua simpul di tingkat (level) yang sama sebelum melanjutkan ke tingkat berikutnya.

Representasi Graf

Graf bisa direpresentasikan dalam dua cara:
	•	Adjacency List (direkomendasikan untuk BFS)
	•	Adjacency Matrix

BFS bekerja baik di graf:
	•	Berarah / Tidak berarah
	•	Terhubung / Tidak terhubung

Langkah-langkah BFS
	1.	Mulai dari node awal
	2.	Masukkan node ke queue
	3.	Tandai node sebagai telah dikunjungi
	4.	Ulangi:
	•	Ambil node dari depan queue
	•	Telusuri semua tetangganya yang belum dikunjungi
	•	Masukkan ke queue dan tandai sebagai dikunjungi

Ilustrasi BFS (dengan Queue)

Misal graf:

     A
    / \
   B   C
  /     \
 D       E

Mulai dari A → BFS urutannya:

Queue: [A] → [B, C] → [C, D] → [D, E] → [E]
Visited: A → B → C → D → E

Implementasi BFS dalam C++

#include <iostream>
#include <vector>
#include <queue>
using namespace std;

void bfs(int start, vector<vector<int>>& adj, vector<bool>& visited) {
    queue<int> q;
    q.push(start);
    visited[start] = true;

    while (!q.empty()) {
        int node = q.front();
        q.pop();
        cout << node << " ";

        for (int neighbor : adj[node]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                q.push(neighbor);
            }
        }
    }
}

int main() {
    int n = 6; // jumlah simpul
    vector<vector<int>> adj(n);

    // Contoh graf tidak berarah
    adj[0] = {1, 2};    // A
    adj[1] = {0, 3};    // B
    adj[2] = {0, 4};    // C
    adj[3] = {1};       // D
    adj[4] = {2, 5};    // E
    adj[5] = {4};       // F

    vector<bool> visited(n, false);
    cout << "Urutan BFS mulai dari simpul 0:\n";
    bfs(0, adj, visited);

    return 0;
}

Analisis Kompleksitas

Aspek	Nilai
Waktu	O(V + E)
Ruang	O(V) untuk queue dan visited

Keterangan:
	•	V = jumlah simpul (vertices)
	•	E = jumlah sisi (edges)

Ciri-ciri BFS

Karakteristik	Keterangan
Strategi	Level-by-level
Struktur data utama	Queue (antrian)
Jalur terpendek?	Ya (untuk graf tak berbobot)
Cocok digunakan saat	Mencari jarak minimum, connected components

Contoh Penggunaan BFS
	•	Mencari jalur terpendek (Shortest Path) dalam peta
	•	Deteksi komponen terhubung
	•	Cek apakah graf bipartite
	•	Puzzle seperti Rubik, 8-puzzle
	•	Web crawler (layanan search engine)



