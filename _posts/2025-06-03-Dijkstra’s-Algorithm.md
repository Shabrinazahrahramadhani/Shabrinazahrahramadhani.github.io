---
title: "DIJKSTRA’S ALGORITHM"
date: 2025-06-03
categories: [DESAIN ANALISIS ALGORITMA, GRAPH]
tags: [daa, algorithm, dijkstra, shortestpath, graph]
---
Pengertian:

Dijkstra adalah algoritma pencarian jalur terpendek dari satu simpul ke semua simpul lainnya dalam graf berbobot dengan bobot non-negatif.

Langkah-langkah:
	1.	Inisialisasi semua jarak sebagai ∞, kecuali simpul awal (0).
	2.	Gunakan priority queue (min-heap) untuk mengambil simpul dengan jarak minimum.
	3.	Perbarui jarak ke tetangga jika ditemukan jarak lebih kecil.
	4.	Ulangi sampai semua simpul telah diproses.

Implementasi C++:

#include <iostream>
#include <vector>
#include <queue>
using namespace std;
typedef pair<int, int> pii;

void dijkstra(int start, vector<vector<pii>>& adj, int n) {
    vector<int> dist(n, INT_MAX);
    dist[start] = 0;

    priority_queue<pii, vector<pii>, greater<pii>> pq;
    pq.push({0, start});

    while (!pq.empty()) {
        int d = pq.top().first;
        int u = pq.top().second;
        pq.pop();

        for (auto [v, weight] : adj[u]) {
            if (dist[v] > d + weight) {
                dist[v] = d + weight;
                pq.push({dist[v], v});
            }
        }
    }

    cout << "Jarak minimum dari simpul " << start << ":\n";
    for (int i = 0; i < n; i++)
        cout << "Simpul " << i << " : " << dist[i] << endl;
}

int main() {
    int n = 5;
    vector<vector<pii>> adj(n);
    adj[0] = {{1, 10}, {4, 5}};
    adj[1] = {{2, 1}};
    adj[2] = {{3, 4}};
    adj[3] = {};
    adj[4] = {{1, 3}, {2, 9}, {3, 2}};

    dijkstra(0, adj, n);
    return 0;
}

Kompleksitas Dijkstra:
	•	Waktu: O((V + E) log V) dengan priority queue
	•	Ruang: O(V) untuk jarak dan antrian
