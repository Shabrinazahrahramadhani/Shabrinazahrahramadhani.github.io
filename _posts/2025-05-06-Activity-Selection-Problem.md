---
title: ACTIVITY SELECTION PROBLEM
date: 06-05-2025
categories: [DESAIN ANALISIS ALGORITMA, GREEDY]
tags: [daa, algorithm, greedy]
---
Pengantar

Dalam dunia yang serba terbatas, kita sering menghadapi masalah penjadwalan—misalnya dalam menyusun jadwal kuliah, pemesanan ruang rapat, atau agenda presentasi. Salah satu permasalahan klasik yang menangani hal tersebut adalah Activity Selection Problem (Masalah Pemilihan Aktivitas).
Tujuannya? Memilih sebanyak mungkin aktivitas yang bisa dilakukan tanpa saling bertabrakan waktu. Masalah ini bisa diselesaikan secara optimal dengan algoritma Greedy.

Konsep Dasar Algoritma

Activity Selection Problem meminta kita untuk memilih jumlah maksimum aktivitas yang tidak tumpang tindih waktunya dari sekumpulan aktivitas yang memiliki:
	•	Waktu mulai (start[i])
	•	Waktu selesai (finish[i])

Inti Strategi Greedy:

Selalu pilih aktivitas yang selesai paling cepat terlebih dahulu. Dengan begitu, kita “menyisakan” waktu sebanyak mungkin untuk aktivitas berikutnya.

Langkah-langkah Algoritma Greedy

	1.	Input: Dua array start[] dan finish[] masing-masing berisi waktu mulai dan selesai dari aktivitas.
	2.	Urutkan aktivitas berdasarkan waktu selesai secara menaik.
	3.	Pilih aktivitas pertama sebagai aktivitas terpilih.
	4.	Untuk setiap aktivitas selanjutnya:
	•	Jika waktu mulainya ≥ waktu selesai dari aktivitas terakhir yang dipilih, maka pilih aktivitas tersebut.
	5.	Ulangi hingga semua aktivitas diperiksa.

⸻

Contoh Kasus

Misalnya:

Start  = {1, 3, 0, 5, 8, 5}
Finish = {2, 4, 6, 7, 9, 9}

Langkah:
	1.	Urutkan aktivitas berdasarkan waktu selesai:

Aktivitas	Start	Finish
A1	           1	2
A2             3	4
A3	           0	6
A4	           5	7
A5	           8	9
A6	           5	9

	2.	Pilih aktivitas pertama (A1).
	3.	A2 dimulai setelah A1 selesai → pilih A2.
	4.	A3 dimulai sebelum A2 selesai → skip.
	5.	A4 dimulai setelah A2 selesai → pilih A4.
	6.	A5 dimulai setelah A4 selesai → pilih A5.
	7.	A6 tumpang tindih dengan A5 → skip.

Aktivitas Terpilih: A1, A2, A4, A5 (Total: 4 aktivitas)

{% raw %}
```cpp
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Item {
    int value, weight;
};

// Mengurutkan berdasarkan rasio nilai/berat
bool compare(Item a, Item b) {
    double r1 = (double)a.value / a.weight;
    double r2 = (double)b.value / b.weight;
    return r1 > r2;
}

double fractionalKnapsack(int W, vector<Item> items) {
    sort(items.begin(), items.end(), compare);

    double totalValue = 0.0;

    for (auto& item : items) {
        if (W >= item.weight) {
            W -= item.weight;
            totalValue += item.value;
        } else {
            totalValue += item.value * ((double)W / item.weight);
            break;
        }
    }

    return totalValue;
}

int main() {
    int W = 50;
    vector<Item> items = {{60, 10}, {100, 20}, {120, 30}};

    cout << "Maximum value we can obtain = " << fractionalKnapsack(W, items) << endl;
    return 0;
}
```
{% endraw %}




Analisis Kompleksitas
	•	Waktu: O(n log n) karena proses sorting.
	•	Ruang: O(1) (jika tidak menghitung array input).






