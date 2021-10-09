package com.demo
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(
    exclude = [DataSourceAutoConfiguration::class]            // enable empty db
)
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args)
}


//lam -> hoc dc nhieu thu

//Ket qua: product + KIEN THUC VE SOFTWARE

// backend (server) --------- REST API --------- front-end (client: mobile app/ webapp)

// Xep hang online

// user  - email/sdt -> userName
        // tao phong -> code
        // join phong -> sau khi join -> stt

// Bac to truong: (Van de: xep hang rat lau)
// 1. Tao phong
// 2. Copy cai code do -> gui qua gr zalo / send sms / mail
// 3 Nguoi dan join phong -> stt

// 7:00 start -> stt 1 notify (nhan qua xong, bac to truong se scan) -> stt2 ->stt3 ->stt4

// Tech stack

// Member
// QH: FE (web) -> mobile app | VueJS, Java (Android), SQL
// Tai: FE (web, mobile), BE | HTML, CSS, JS, Bootstrap lib, SQL, Java (Android)
// HH: FE (web) | VueJS, HTML, CSS, regex, SQL
// Dat: FE (web), BE (Java/Kotlin) | web, restAPI, DB-SQL, Deployment (Heroku, Firebase)
// Lam: BE (Java/Kotlin), FE (Mobile) | REST-API, DB, Mobile, Encrypt

//Backend Server
//Framework: SpringBoot (Java, kotlin) -> bind DB (SQL) -> REST API / Websocket (realtime, chat, expose status room)
// package manager (java): gradle, maven (~ npm)


//FrontEnd
// WebApp: VueJS, (Logic - UI), state-management (Tai): Design, Dev UI, Dev *Utils (handle rest api, encrypt data), state-organization (Codebase)
// Mobile app React-native (android-ios), dung chung utils voi web dc
// Design theme
// Testers


//Progress

// 1. BA
//      - define flow (Ex: login -> man hinh A -> B ....)
//      - split modules

//  1.5 Lua chon cong nghe

// 2. Desgin DB - Design UI

// 3. Nhay vao code BE (mock data) / FE

// 4. Rap

// 5. Test

// 6. Them tinh nang + Back ve buoc so 3





