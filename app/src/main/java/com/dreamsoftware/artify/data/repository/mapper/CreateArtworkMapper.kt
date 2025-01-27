package com.dreamsoftware.artify.data.repository.mapper

import com.dreamsoftware.brownie.utils.IBrownieOneSideMapper
import com.dreamsoftware.artify.data.remote.dto.CreateArtworkDTO
import com.dreamsoftware.artify.domain.model.CreateArtworkBO
import com.dreamsoftware.artify.domain.model.ArtworkMessageRoleEnum

internal class CreateArtworkMapper: IBrownieOneSideMapper<CreateArtworkBO, CreateArtworkDTO> {
    override fun mapInListToOutList(input: Iterable<CreateArtworkBO>): Iterable<CreateArtworkDTO> =
        input.map(::mapInToOut)

    override fun mapInToOut(input: CreateArtworkBO): CreateArtworkDTO = with(input) {
        CreateArtworkDTO(
            uid = uid,
            userId = userId,
            imageUrl = imageUrl,
            imageDescription = imageDescription,
            question = question,
            questionRole = ArtworkMessageRoleEnum.USER.name,
            answer = answer,
            answerRole = ArtworkMessageRoleEnum.MODEL.name
        )
    }
}