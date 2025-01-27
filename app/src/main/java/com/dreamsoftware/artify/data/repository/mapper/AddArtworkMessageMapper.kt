package com.dreamsoftware.artify.data.repository.mapper

import com.dreamsoftware.brownie.utils.IBrownieOneSideMapper
import com.dreamsoftware.artify.data.remote.dto.AddArtworkMessageDTO
import com.dreamsoftware.artify.domain.model.AddArtworkMessageBO
import com.dreamsoftware.artify.domain.model.ArtworkMessageRoleEnum

internal class AddArtworkMessageMapper: IBrownieOneSideMapper<AddArtworkMessageBO, AddArtworkMessageDTO> {
    override fun mapInListToOutList(input: Iterable<AddArtworkMessageBO>): Iterable<AddArtworkMessageDTO> =
        input.map(::mapInToOut)

    override fun mapInToOut(input: AddArtworkMessageBO): AddArtworkMessageDTO = with(input) {
        AddArtworkMessageDTO(
            uid = uid,
            userId = userId,
            question = question,
            questionRole = ArtworkMessageRoleEnum.USER.name,
            answer = answer,
            answerRole = ArtworkMessageRoleEnum.MODEL.name
        )
    }
}