package hr.element.onebyseven
package countryripper
package iso

import common._
import dispatch._

import scalax.file._

object ISORipper extends Ripper[ISOCountry] {
  val ISOURI =
    :/("www.iso.org") / "iso" / "list-en1-semic-3.txt"

  def ripCountries() =
    Http(ISOURI >\ ("ISO-8859-1") >- {body =>
      val lines = body split "\r\n" toList

      lines drop(2) map{line =>
        val t = line split ';'
        val name = t.head
        val a2 = Alpha2(t.last)
        new ISOCountry(name, a2)
      }
    }) toIndexedSeq
}
