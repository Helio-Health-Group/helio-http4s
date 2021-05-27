package com.helio.models

import java.time.LocalDateTime

case class ServerInfo(msg: String,
                      name: String = "Helio-Http",
                      timestamp: LocalDateTime = LocalDateTime.now())
