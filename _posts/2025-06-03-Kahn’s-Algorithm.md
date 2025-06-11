---
title: KAHN’S ALGORITHM (TOPOLOGICAL SORT)
date: 2025-06-03
categories: [DESAIN ANALISIS ALGORITMA, GRAPH]
tags: [daa, algorithm, topologicalsort, kahn, graph]
---
Pengertian:

Kahn’s Algorithm digunakan untuk melakukan topological sorting pada graf berarah dan asiklik (DAG).

Langkah-langkah:
	1.	Hitung in-degree semua simpul.
	2.	Masukkan semua simpul dengan in-degree = 0 ke queue.
	3.	Hapus simpul dari queue, cetak, dan kurangi in-degree tetangganya.
	4.	Ulangi hingga queue kosong.

Implementasi C++:

#include <iostream>
#include <vector>
#include <queue>
using namespace std;

void kahnTopoSort(int n, vector<vector<int>>& adj) {
    vector<int> inDegree(n, 0);
    for (auto list : adj)
        for (int v : list)
            inDegree[v]++;

    queue<int> q;
    for (int i = 0; i < n; i++)
        if (inDegree[i] == 0) q.push(i);

    while (!q.empty()) {
        int u = q.front();
        q.pop();
        cout << u << " ";

        for (int v : adj[u]) {
            inDegree[v]--;
            if (inDegree[v] == 0) q.push(v);
        }
    }
}

int main() {
    int n = 6;
    vector<vector<int>> adj(n);
    adj[5] = {0, 2};
    adj[4] = {0, 1};
    adj[2] = {3};
    adj[3] = {1};

    cout << "Topological Sort (Kahn):\n";
    kahnTopoSort(n, adj);

    return 0;
}

Kompleksitas Kahn:
	•	Waktu: O(V + E)
	•	Ruang: O(V) (queue + inDegree)

