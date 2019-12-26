package fintech.assignment09

import java.util.UUID

import scala.concurrent.{ExecutionContext, Future, Promise}
import scala.util.Try

case class Tweet(id: UUID,
                 user: String,
                 text: String,
                 likes: Int)


trait TweetApi {
  /**
    * отправить твит
    * возвращает UUID твита либо текст ошибки
    *
    * @param user
    * @param text
    * @return
    */
  def send(user: String, text: String): Future[Either[String, UUID]]

  /**
    * получить твит
    * возвращает твиты и тексты ошибок с идентификаторами
    *
    * @param id
    * @return
    */
  def get(id: Set[UUID]): Future[(Set[(UUID, String)], Set[Tweet])]

  /**
    * лайкнуть твит
    * возвращает флаг принятия либо текст ощибки
    *
    * @param id
    * @return
    */
  def like(id: UUID): Future[Either[String, Boolean]]

  /**
    * дизлайкнуть твит
    * возвращает флаг принятия либо текст ощибки
    *
    * @param id
    * @return
    */
  def unlike(id: UUID): Future[Either[String, Boolean]]

  /**
    * лайкнуть твиты.
    *
    * @param idList
    * @return
    */
  def likeSeq(idList: Set[UUID]): Future[Either[Set[(UUID, String)], Set[(UUID, Boolean)]]]
}

object TweetApi {
  def apply(remote: TwitterRemote)(implicit ec: ExecutionContext): TweetApi = ???
}
