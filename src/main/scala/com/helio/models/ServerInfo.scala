package com.helio.models

import java.time.LocalDateTime

case class ServerInfo(msg: String,
                      name: String = "Helio-Http",
                      timestamp: Option[LocalDateTime] = Option(LocalDateTime.now()))
