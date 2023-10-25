package com.devmasterteam.motivation.repository

import com.brunokilo.motivation.utils.MotivationEnum
import kotlin.random.Random

data class Phrase(val description: String, val category: MotivationEnum)

class Mock {

    private val listPhrases: List<Phrase> = listOf(
        Phrase("Não sabendo que era impossível, foi lá e fez.", MotivationEnum.HAPPY),
        Phrase("Você não é derrotado quando perde, você é derrotado quando desiste!", MotivationEnum.HAPPY),
        Phrase("Quando está mais escuro, vemos mais estrelas!", MotivationEnum.HAPPY),
        Phrase("Insanidade é fazer sempre a mesma coisa e esperar um resultado diferente.", MotivationEnum.HAPPY),
        Phrase("Não pare quando estiver cansado, pare quando tiver terminado.", MotivationEnum.HAPPY),
        Phrase("O que você pode fazer agora que tem o maior impacto sobre o seu sucesso?", MotivationEnum.HAPPY),
        Phrase("A melhor maneira de prever o futuro é inventá-lo.", MotivationEnum.SUNNY),
        Phrase("Você perde todas as chances que você não aproveita.", MotivationEnum.SUNNY),
        Phrase("Fracasso é o condimento que dá sabor ao sucesso.", MotivationEnum.SUNNY),
        Phrase(" Enquanto não estivermos comprometidos, haverá hesitação!", MotivationEnum.SUNNY),
        Phrase("Se você não sabe onde quer ir, qualquer caminho serve.", MotivationEnum.SUNNY),
        Phrase("Se você acredita, faz toda a diferença.", MotivationEnum.SUNNY),
        Phrase("Riscos devem ser corridos, porque o maior perigo é não arriscar nada!", MotivationEnum.SUNNY)
    )

    // Obtém frase aleatória de acordo com o filtro
    fun getPhrase(value: MotivationEnum): String {
        val filtered = when(value) {
            MotivationEnum.HAPPY -> listPhrases.filter { it.category == MotivationEnum.HAPPY}
            MotivationEnum.SUNNY -> listPhrases.filter { it.category == MotivationEnum.SUNNY}
            else -> { listPhrases }
        }

        // Número aleatório de 0 ao tamanho da lista retornada do filtro
        val rand = Random.nextInt(filtered.size)

        // Retorna string
        return filtered[rand].description
    }

}