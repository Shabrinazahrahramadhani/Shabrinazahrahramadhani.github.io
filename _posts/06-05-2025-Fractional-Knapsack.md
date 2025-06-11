---
Title: FRACTIONAL KNAPSACK
Date: 06-05-2025
Categories: [DESAIN ANALISIS ALGORITMA, GREEDY]
Tags: [daa, algorithm, greedy]
---
Pengantar

Bayangkan kamu seorang pencuri yang masuk ke sebuah rumah dan memiliki tas (knapsack) dengan kapasitas terbatas. Di depanmu ada berbagai barang dengan nilai (value) dan berat (weight) berbeda. Tujuanmu? Mengambil barang sebanyak mungkin agar nilai total maksimum, namun tetap tidak melebihi kapasitas tas.

Jika kamu boleh mengambil sebagian barang (misalnya setengah dari emas batangan), maka masalah ini disebut:

Fractional Knapsack Problem
dan dapat diselesaikan dengan algoritma Greedy.

Konsep Dasar Algoritma

Diberikan:
	•	Kapasitas tas: W
	•	n buah barang dengan masing-masing:
	•	Nilai (value[i])
	•	Berat (weight[i])

Tujuan:
	•	Ambil barang secara penuh atau sebagian agar:
	•	Total berat ≤ W
	•	Total nilai maksimum

Strategi Greedy:
	1.	Hitung value per weight (nilai per satuan berat) untuk setiap barang.
	2.	Urutkan barang berdasarkan value per weight secara menurun.
	3.	Ambil sebanyak mungkin dari barang dengan value per weight tertinggi.

Langkah-langkah Algoritma
	1.	Hitung value[i]/weight[i] untuk semua barang.
	2.	Urutkan barang berdasarkan rasio tersebut (dari tinggi ke rendah).
	3.	Inisialisasi total value = 0 dan kapasitas tersisa = W.
	4.	Iterasi:
	•	Jika berat barang ≤ kapasitas tersisa → ambil seluruhnya.
	•	Jika tidak → ambil sebagian hingga kapasitas penuh.
	5.	Tambahkan nilai yang diambil ke total value.

Contoh Kasus

Barang ke- | Nilai | Berat | Nilai/Berat
-----------|--------|--------|--------------
    1      |  60   |  10   |     6.0
    2      | 100   |  20   |     5.0
    3      | 120   |  30   |     4.0

Kapasitas tas = W = 50

Langkah:
	•	Urutkan berdasarkan nilai/berat: Barang 1, 2, 3
	•	Ambil Barang 1 → sisa kapasitas: 40 → nilai: 60
	•	Ambil Barang 2 → sisa kapasitas: 20 → nilai: 160
	•	Ambil 2/3 dari Barang 3 (karena 30 > 20) → tambah nilai: 2/3 × 120 = 80
	•	✅ Total Nilai = 240

Implementasi dalam C++

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Item {
    int value, weight;
    // Rasio nilai per berat
    double ratio() const {
        return (double)value / weight;
    }
};

// Urutkan berdasarkan rasio tertinggi
bool compare(Item a, Item b) {
    return a.ratio() > b.ratio();
}

double fractionalKnapsack(int W, vector<Item>& items) {
    sort(items.begin(), items.end(), compare);

    double totalValue = 0.0;

    for (auto& item : items) {
        if (W == 0) break;

        if (item.weight <= W) {
            totalValue += item.value;
            W -= item.weight;
        } else {
            totalValue += item.ratio() * W;
            W = 0;
        }
    }

    return totalValue;
}

int main() {
    vector<Item> items = {{60, 10}, {100, 20}, {120, 30}};
    int capacity = 50;

    double maxValue = fractionalKnapsack(capacity, items);
    cout << "Total nilai maksimum: " << maxValue << endl;

    return 0;
}

 Analisis Kompleksitas
	•	Waktu: O(n log n) (karena sorting)
	•	Ruang: O(1) tambahan jika in-place



