---
title: HUFFMAN CODING
date: 20-05-2025
categories: [DESAIN ANALISIS ALGORITMA, GREEDY]
tags: [daa, algorithm, greedy, compression]
---
Pengantar

Dalam era digital, efisiensi penyimpanan dan pengiriman data sangat penting. Salah satu teknik penting dalam kompresi data tanpa kehilangan informasi adalah:

Huffman Coding

Algoritma ini digunakan dalam berbagai aplikasi seperti kompresi file (ZIP), gambar (JPEG), video (MPEG), dan lainnya. Ia bekerja dengan memberi kode lebih pendek untuk simbol yang sering muncul dan kode lebih panjang untuk simbol yang jarang — inilah inti dari kompresi lossless.

Konsep Dasar Huffman Coding

Diberikan:
	•	Sekumpulan karakter/simbol, masing-masing dengan frekuensi kemunculan.

Tujuan:
	•	Menciptakan kode biner untuk setiap karakter sehingga:
	•	Tidak ada kode yang merupakan prefiks dari kode lain (Prefix-Free Code)
	•	Total panjang rata-rata kode minimum → Kompresi efisien

Langkah-langkah Algoritma Huffman
	1.	Buat node untuk setiap karakter, berisi: karakter, frekuensi.
	2.	Masukkan semua node ke priority queue (min-heap) berdasarkan frekuensi.
	3.	Ulangi sampai hanya 1 node tersisa:
	•	Ambil 2 node dengan frekuensi terendah
	•	Gabungkan menjadi node baru dengan frekuensi = jumlah keduanya
	•	Tambahkan node baru ke heap
	4.	Hasilnya adalah pohon Huffman (Huffman Tree).
	5.	Dari akar ke daun, beri label kiri = 0, kanan = 1
	6.	Kode Huffman untuk tiap karakter = jejak biner dari akar ke daun

Contoh Kasus

Input:

Karakter	Frekuensi
A	            5
B	            9
C	            12
D	            13
E	            16
F	            45

Langkah-langkah:
	1.	Gabung A(5) + B(9) → AB(14)
	2.	Gabung C(12) + AB(14) → CAB(26)
	3.	Gabung D(13) + E(16) → DE(29)
	4.	Gabung CAB(26) + DE(29) → CABDE(55)
	5.	Gabung CABDE(55) + F(45) → Root(100)

Kode Huffman (hasil akhir bisa berbeda tergantung sisi kiri/kanan tapi panjang tetap optimal):

Karakter	Kode
F	        0
C	        100
A	        1100
B	        1101
D	        1010
E	        1011


Implementasi C++

#include <iostream>
#include <queue>
#include <unordered_map>
using namespace std;

struct Node {
    char ch;
    int freq;
    Node *left, *right;

    Node(char c, int f) : ch(c), freq(f), left(nullptr), right(nullptr) {}
};

// Comparator untuk priority queue
struct Compare {
    bool operator()(Node* a, Node* b) {
        return a->freq > b->freq;
    }
};

// Fungsi rekursif untuk menghasilkan kode
void generateCodes(Node* root, string code, unordered_map<char, string>& huffmanCode) {
    if (!root) return;
    if (!root->left && !root->right) {
        huffmanCode[root->ch] = code;
    }
    generateCodes(root->left, code + "0", huffmanCode);
    generateCodes(root->right, code + "1", huffmanCode);
}

void huffmanCoding(string text, unordered_map<char, string>& huffmanCode) {
    // Hitung frekuensi karakter
    unordered_map<char, int> freq;
    for (char ch : text) freq[ch]++;

    priority_queue<Node*, vector<Node*>, Compare> pq;

    for (auto pair : freq) {
        pq.push(new Node(pair.first, pair.second));
    }

    // Bangun pohon Huffman
    while (pq.size() > 1) {
        Node* left = pq.top(); pq.pop();
        Node* right = pq.top(); pq.pop();

        Node* merged = new Node('\0', left->freq + right->freq);
        merged->left = left;
        merged->right = right;

        pq.push(merged);
    }

    Node* root = pq.top();
    generateCodes(root, "", huffmanCode);
}

int main() {
    string text = "ABRACADABRA";
    unordered_map<char, string> huffmanCode;
    huffmanCoding(text, huffmanCode);

    cout << "Kode Huffman:\n";
    for (auto pair : huffmanCode) {
        cout << pair.first << " : " << pair.second << "\n";
    }

    return 0;
}

Analisis Kompleksitas
	•	Waktu:
	•	Hitung frekuensi: O(n)
	•	Bangun heap: O(d log d) (d = jumlah karakter unik)
	•	Bangun pohon: O(d log d)
	•	Ruang:
	•	Penyimpanan pohon dan kode: O(d)

Visualisasi
	•	Gunakan pohon biner:
	•	Setiap simpul gabungan ditampilkan dengan jumlah frekuensi
	•	Daun: karakter & kode biner hasil penelusuran

