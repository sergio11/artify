package com.dreamsoftware.artify.data.repository.mapper

import com.dreamsoftware.brownie.utils.IBrownieOneSideMapper
import com.dreamsoftware.artify.data.remote.dto.AddInquizeMessageDTO
import com.dreamsoftware.artify.domain.model.AddInquizeMessageBO
import com.dreamsoftware.artify.domain.model.InquizeMessageRoleEnum

internal class AddInquizeMessageMapper: IBrownieOneSideMapper<AddInquizeMessageBO, AddInquizeMessageDTO> {
    override fun mapInListToOutList(input: Iterable<AddInquizeMessageBO>): Iterable<AddInquizeMessageDTO> =
        input.map(::mapInToOut)

    override fun mapInToOut(input: AddInquizeMessageBO): AddInquizeMessageDTO = with(input) {
        AddInquizeMessageDTO(
            uid = uid,
            userId = userId,
            question = question,
            questionRole = InquizeMessageRoleEnum.USER.name,
            answer = answer,
            answerRole = InquizeMessageRoleEnum.MODEL.name
        )
    }
}