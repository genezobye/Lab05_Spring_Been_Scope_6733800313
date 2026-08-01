# Coffee Menu Service

REST API สำหรับจัดการเมนูร้านกาแฟ (เพิ่ม / ดู / แก้ไข / ลบ) พัฒนาด้วย Spring Boot
โปรเจกต์นี้ทำเป็นส่วนหนึ่งของวิชา CP353002 — Principles of Software Design and Development

## เทคโนโลยีที่ใช้

- Java 17
- Spring Boot (Spring Web)
- Maven
- เก็บข้อมูลใน memory ด้วย `List` (ยังไม่ใช้ฐานข้อมูล)

## โครงสร้างโปรเจกต์ (Layered Design)

```
src/main/java/com/example/cp6733805127_2/
├── model/
│   └── Coffee.java              # โครงสร้างข้อมูลเมนูกาแฟ
├── service/
│   └── CoffeeService.java       # เก็บ logic และ List<Coffee> ใน memory
├── controller/
│   └── CoffeeController.java    # รับ HTTP request แล้วเรียก Service
└── Cp67338051272Application.java
```

## วิธีรันโปรเจกต์

1. Clone repo นี้ลงเครื่อง
2. เข้าไปที่โฟลเดอร์โปรเจกต์
3. รันคำสั่ง:

   ```bash
   ./mvnw spring-boot:run
   ```

   (Windows PowerShell ใช้ `.\mvnw.cmd spring-boot:run`)

4. แอปจะรันที่ `http://localhost:8080`

เมื่อเริ่มแอป ระบบจะใส่ข้อมูลตัวอย่างไว้ล่วงหน้า 2 รายการ (Espresso, Latte) เพื่อให้ทดสอบ GET ได้ทันที

## Endpoints

| # | Method | Path | คำอธิบาย |
|---|--------|------|----------|
| 1 | GET | `/coffees` | ดูเมนูทั้งหมด |
| 2 | GET | `/coffees/{id}` | ดูเมนู 1 รายการตาม id |
| 3 | POST | `/coffees` | เพิ่มเมนูใหม่ |
| 4 | PUT | `/coffees/{id}` | แก้ไขเมนูเดิมตาม id |
| 5 | DELETE | `/coffees/{id}` | ลบเมนูตาม id |
| bonus | GET | `/coffees/search?name=...` | ค้นหาเมนูตามชื่อ |

หากหา `id` ไม่เจอ API จะคืนค่า `404 Not Found`

## ตัวอย่างการเรียกใช้งาน (curl)

**ดูเมนูทั้งหมด**
```bash
curl http://localhost:8080/coffees
```

**ดูเมนูตาม id**
```bash
curl http://localhost:8080/coffees/1
```

**เพิ่มเมนูใหม่**
```bash
curl -X POST http://localhost:8080/coffees \
     -H "Content-Type: application/json" \
     -d '{"name":"Cappuccino","price":60}'
```

**แก้ไขเมนู**
```bash
curl -X PUT http://localhost:8080/coffees/2 \
     -H "Content-Type: application/json" \
     -d '{"name":"Latte","price":50}'
```

**ลบเมนู**
```bash
curl -X DELETE http://localhost:8080/coffees/3
```

**ค้นหาเมนูตามชื่อ (bonus)**
```bash
curl "http://localhost:8080/coffees/search?name=latte"
```

> หมายเหตุ: ถ้ารันบน Windows PowerShell คำสั่ง `curl` จะเป็น alias ของ `Invoke-WebRequest`
> แนะนำให้ใช้ `Invoke-RestMethod` แทน หรือเรียก `curl.exe` ตรงๆ เพื่อให้ syntax ตรงกับตัวอย่างข้างต้น

## ตัวอย่างผลลัพธ์

```
GET /coffees   → 200 OK
[
  { "id": 1, "name": "Espresso", "price": 45.0 },
  { "id": 2, "name": "Latte",    "price": 55.0 }
]
```

## ผู้จัดทำ

- ชื่อ-นามสกุล: _นาย คณิศร มาประจักษ์_
- รหัสนักศึกษา: _673380031-3_
- วิชา: CP353002 — Principles of Software Design and Development
