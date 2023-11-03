# Job4j_Util_File_searcher
### ��� ��������� ������� ��� ������ ������


___
### ����������� �������.

1. ��� ������� ����������� �����, � ������� ����� �������� ����� ������, �������� c:\project\job4j\ <br><br>
2. � �������� ������ ���������:
   - ������� ������;
   - ��� ������:
     - mask: ����� �� ����� �����;
     - name: ����� �� ����� �����;
     - regex: ����� �� ����������� ���������;
   - ���� � ��������� �����, ����������� ���������� ������. <br><br>
3. ������ �������:

 > -d=c:\project\job4j\&nbsp;&nbsp;-c=.+\.class\Z&nbsp;&nbsp;-t=regex&nbsp;&nbsp;-o=c:\project\find.txt

 - -d {directory} : ���� � �������� �����, � ������� ����� ����������� ����� ������;
 - -c {condition} : ������� ������, �������� ����;
 - -t {search type} : ��� ������ ������: [mask, name, regex];
 - -o {output} : ���� � ��������� ����� � ����������� ������. ���� ����� ����� ���� �� ����������: [txt, doc, rtf].

<br>

___
### ���������� � ���������

- Java 11
- Maven v.3.6.3

<br>

---
### ������ �������

1. ������� ����� ������� � github �� ������ � ��������������� � ��������� ����������:<br>
   [https://github.com/MasterMaxTs/job4j_design_utils/archive](https://github.com/MasterMaxTs/job4j_design_utils/archive/refs/heads/master.zip)


2. ������� � ���������� �������, � ����� <b>file_searcher</b> ������� ��������� ������ <b>�� ����� ��������������</b>.</br>
    - ��� <ins>�������</ins> ������� ������� ��������� ��������������� �������:
        - ```mvn package -Dmaven.test.skip=true```
        - ```java -jar target/file_searcher.jar -d=<ROOT_FOLDER> -c=<CONDITION> -t=<SEARCH_TYPE> -o=<TARGET_RESULT_FILE_>```

    - ��� <ins>������������</ins> ������� ���������� ��������� �������:
        - ```java -jar target/file_searcher.jar -d=<ROOT_FOLDER> -c=<CONDITION> -t=<SEARCH_TYPE> -o=<TARGET_RESULT_FILE_>```

<br>

___
### �������������� � ��������
<br>

1. ��� �� �������: ����� ������ �� �����:<br><br>
   ![img.png](img/find-by-mask-success.JPG)


2. ��� �� �������: ����� ������ �� �����:<br><br>
   ![img.png](img/find-by-name-success.JPG)


3. ��� �� �������: ����� ������ �� ����������� ���������:<br><br>
   ![img.png](img/find-by-regex-success.JPG)


4. ��� �� �������: �������������� ��������, ��������� � ��������� ���������������� ������ ������:<br><br>
 - �� ������� ��������� ��������� ������:
<br><br>
   - ![img.png](img/error-missing-key.JPG)


- �� ������� �������� �����:
<br><br>
    - ![img.png](img/error-key-without-value.JPG)


- ������o �������� �������� �����:
<br><br>
    - ![img.png](img/error-invalid-key-name.JPG)


- ������o �������� �������� ����� �������� ����������:
<br><br>
    - ![img.png](img/error-invalid-key(-d)-value.JPG)


- ������o �������� �������� ����� ���� ������:
  <br><br>
    - ![img.png](img/error-invalid-key-value.JPG)


- ������o �������� �������� ����� ���� � ��������������� ����� (���������������� ���������� �����):
  <br><br>
    - ![img.png](img/error-invalid-target-file-extension.JPG)
  

- ������� ����� � ���������� ���������:
  <br><br>
    - ![img.png](img/error-find-duplicate-keys.JPG)

___
### ��������
* Email: java.dev-maxim.tsurkanov@yandex.ru
* Skype: https://join.skype.com/invite/ODADx0IJ3BBu
* VK: https://m.vk.com/id349328153
* Telegram: matsurkanov