# Job4j_Util_CSVReader
### ��� ������� ��� �������� ������ ���������� ������� csv � ����������� ����������


___
### ����������� �������.

1. ��� ������� ����������� ���� � ����� ��� ��������, �������� c:\project\job4j\data.csv <br><br>

2. � �������� ������ ���������:
    - �����������;
    - ������ �� ��������;
    - ������� ������. ���� ����� ������ ��� ���������� ��������: stdout (����� � �������) ��� ���� � �����, ���� ����� ������� ���������. <br><br>
   
3. ������ �������:

>  -ptf=file.csv&nbsp;&nbsp;-del=;&nbsp;&nbsp;-out=stdout&nbsp;&nbsp;-fil=name,age

- -ptf {path to file} : ���� � ��������� �����;
- -del {delimiter} : ����������� ����������� ������;
- -out {output}: ������� �������� ������;
- -fil {filter} : ������ �������� ��� ����������� � �������� ������.

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


2. ������� � ���������� �������, � ����� <b>csv_reader</b> ������� ��������� ������ <b> �� ����� ��������������</b>.</br>
    - ��� <ins>�������</ins> ������� ������� ��������� ��������������� �������:
        - ```mvn package -Dmaven.test.skip=true```
        - ```java -jar target/csv_reader.jar -ptf=<PATH_TO_FILE> -del=<DATA_DELIMITER> -out=<OUTPUT_RESULT> -fil=<DATA_FILTER>```

    - ��� <ins>������������</ins> ������� ���������� ��������� �������:
        - ```java -jar target/csv_reader.jar -ptf=<PATH_TO_FILE> -del=<DATA_DELIMITER> -out=<OUTPUT_RESULT> -fil=<DATA_FILTER>```

<br>

___
### �������������� � ��������
<br>

1. ��� �� �������: ��������� �������� �����:<br><br>
   ![img.png](img/input-data.JPG)


2. ��� �� �������: �������� ������� ����� (����� � �������):<br><br>
   ![img.png](img/read-csv-to-console-success.JPG)



3. ��� �� �������: �������� ������� ����� (����� � ����):<br><br>
   ![img.png](img/read-csv-to-file-success.JPG)



4. ��� �� �������: �������������� ��������, ��������� � ��������� ���������������� ������ ������:
- �� ��������� ������� ��������� ��������� ������:
  <br><br>
    - ![img.png](img/error-missing-key.JPG)


- ������o �������� ��� �����:
  <br><br>
    - ![img.png](img/error-invalid-key-name.JPG)



- ������o �������� �������� ����� ���� � �������� �����:
  <br><br>
    - ![img.png](img/error-invalid-key(-ptf)-value.JPG)



- ������o �������� �������� ����� ����������� ������:
  <br><br>
    - ![img.png](img/error-invalid-key(-del)-value.JPG)



- ������o �������� �������� ����� ������ �������� ������:
  <br><br>
    - ![img.png](img/error-invalid-key(-fil)-value.JPG)



- ������o �������� �������� ����� ������� �������� ������:
  <br><br>
    - ![img.png](img/error-invalid-key(-out)-value.JPG)



- ������o �������� �������� ����� ������� �������� ������:
  <br><br>
    - ![img.png](img/error-invalid-key(-out)-value-2.JPG)



- �� ������� �������� �����:
  <br><br>
    - ![img.png](img/error-key-without-value.JPG)



- ������� ����� � ���������� ���������:
  <br><br>
    - ![img.png](img/error-find-duplicate-keys.JPG)


___
### ��������
* Email: java.dev-maxim.tsurkanov@yandex.ru
* Skype: https://join.skype.com/invite/ODADx0IJ3BBu
* VK: https://m.vk.com/id349328153
* Telegram: matsurkanov