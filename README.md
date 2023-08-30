# PdfAnalyzer
此應用程式使用 java 搭配 PDF Box libary 開發，將 PDF 文件結構解析並輸出成 json 檔案。

## Usage
```
java -jar PdfAnalyzer <pdf file path> -range <page range> -output <output file path>
```
* pdf file path: 要解析的pdf 檔案路徑。
* page range: 欲解析的頁碼範圍，可為單頁或一段範圍 ex: 1, 3-4，若有多段請使用 , 隔開。
* output file path: 輸出的 json 檔案路徑，若無指定將在 console 輸出結果。

## Example

```
java -jar PdfAnalyzer input.pdf -range 1-2, 3-5 -output output.json
```