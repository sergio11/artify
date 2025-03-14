package com.dreamsoftware.artify.data.remote.mapper

import com.dreamsoftware.brownie.utils.IBrownieOneSideMapper
import com.dreamsoftware.artify.data.remote.dto.AddArtworkMessageDTO
import java.util.UUID

internal class AddArtworkMessageRemoteMapper: IBrownieOneSideMapper<AddArtworkMessageDTO, List<Map<String, String>>> {

    private companion object {
        const val MESSAGE_ID_KEY = "uid"
        const val ROLE_KEY = "role"
        const val TEXT_KEY = "text"
    }

    override fun mapInToOut(input: AddArtworkMessageDTO): List<Map<String, String>> = with(input) {
        listOf(
            hashMapOf(
                MESSAGE_ID_KEY to UUID.randomUUID().toString(),
                ROLE_KEY to questionRole,
                TEXT_KEY to question
            ),
            hashMapOf(
                MESSAGE_ID_KEY to UUID.randomUUID().toString(),
                ROLE_KEY to answerRole,
                TEXT_KEY to answer
            )
        )
    }

    override fun mapInListToOutList(input: Iterable<AddArtworkMessageDTO>): Iterable<List<Map<String, String>>> =
        input.map(::mapInToOut)
}