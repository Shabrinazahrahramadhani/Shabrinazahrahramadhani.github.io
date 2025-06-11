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

Implementasi dalam C++

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

struct Activity {
    int start, finish;
};

// Fungsi untuk mengurutkan aktivitas berdasarkan waktu selesai
bool compare(Activity a, Activity b) {
    return a.finish < b.finish;
}

void activitySelection(vector<Activity>& activities) {
    sort(activities.begin(), activities.end(), compare);

    cout << "Aktivitas yang dipilih:\n";
    int lastFinishTime = activities[0].finish;
    cout << "(" << activities[0].start << ", " << activities[0].finish << ")\n";

    for (int i = 1; i < activities.size(); i++) {
        if (activities[i].start >= lastFinishTime) {
            cout << "(" << activities[i].start << ", " << activities[i].finish << ")\n";
            lastFinishTime = activities[i].finish;
        }
    }
}

int main() {
    vector<Activity> activities = {{1, 2}, {3, 4}, {0, 6}, {5, 7}, {8, 9}, {5, 9}};
    activitySelection(activities);
    return 0;
}


Analisis Kompleksitas
	•	Waktu: O(n log n) karena proses sorting.
	•	Ruang: O(1) (jika tidak menghitung array input).






