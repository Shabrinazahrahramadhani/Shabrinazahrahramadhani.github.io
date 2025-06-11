---
title: DEPTH-FIRST SEARCH (DFS)
date: 27-05-2025
categories: [DESAIN ANALISIS ALGORITMA, GRAPH]
tags: [daa, algorithm, dfs, graph]
---
Pengertian:

DFS adalah algoritma penelusuran graf dengan strategi masuk sedalam mungkin terlebih dahulu, baru kembali (backtrack) jika sudah tidak ada jalur.

Langkah-langkah DFS:
	1.	Mulai dari simpul awal.
	2.	Tandai simpul sebagai dikunjungi.
	3.	Kunjungi simpul tetangga yang belum dikunjungi secara rekursif.
	4.	Backtrack jika semua tetangga sudah dikunjungi.

Implementasi C++:

#include <iostream>
#include <vector>
using namespace std;

void dfs(int node, vector<vector<int>>& adj, vector<bool>& visited) {
    visited[node] = true;
    cout << node << " ";

    for (int neighbor : adj[node]) {
        if (!visited[neighbor])
            dfs(neighbor, adj, visited);
    }
}

int main() {
    int n = 5;
    vector<vector<int>> adj(n);
    adj[0] = {1, 2};
    adj[1] = {0, 3};
    adj[2] = {0, 4};
    adj[3] = {1};
    adj[4] = {2};

    vector<bool> visited(n, false);
    cout << "Hasil DFS:\n";
    dfs(0, adj, visited);

    return 0;
}

Kompleksitas DFS:
	•	Waktu: O(V + E)
	•	Ruang: O(V) (stack + visited)
