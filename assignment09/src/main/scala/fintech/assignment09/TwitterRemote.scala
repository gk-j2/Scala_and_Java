package fintech.assignment09

import java.util.UUID

import scala.util.Try

trait TwitterRemote {
  type Id = UUID
  type User = String
  type Text = String
  type LikeCount = Int

  def send(text: Text, user: User)(cb: Try[Id] => Unit): Unit
  def like(id: Id)(cb: Try[Boolean] => Unit)
  def unlike(id: Id)(cb: Try[Boolean] => Unit)
  def get(id: Id)(cb: Try[(Id,User,Text,LikeCount)] => Unit)
}
