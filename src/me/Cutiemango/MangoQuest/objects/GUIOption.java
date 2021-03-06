package me.Cutiemango.MangoQuest.objects;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import org.bukkit.entity.Player;
import me.Cutiemango.MangoQuest.book.InteractiveText;
import me.Cutiemango.MangoQuest.manager.QuestChatManager;
import me.Cutiemango.MangoQuest.manager.RequirementManager;
import me.Cutiemango.MangoQuest.objects.requirement.RequirementType;
import me.Cutiemango.MangoQuest.objects.trigger.TriggerObject;
import me.Cutiemango.MangoQuest.objects.trigger.TriggerTask;
import net.citizensnpcs.api.npc.NPC;

public class GUIOption
{
	public GUIOption(String ID, String display, List<TriggerObject> clickevt)
	{
		internalID = ID;
		displayText = QuestChatManager.translateColor(display);
		hoverText = "";
		clickEvent = clickevt;
	}
	
	private String internalID;
	private String displayText;
	private String hoverText;
	private EnumMap<RequirementType, Object> requirementMap = new EnumMap<>(RequirementType.class);
	private List<TriggerObject> clickEvent;
	
	public InteractiveText toInteractiveText(NPC npc)
	{
		if (displayText == null)
			return new InteractiveText("");
		InteractiveText text = new InteractiveText(displayText);
		text.showText(hoverText);
		text.clickCommand("/mq q option " + npc.getId() + " " + internalID);
		return text;
	}
	
	public void setRequirementMap(EnumMap<RequirementType, Object> requirementMap)
	{
		this.requirementMap = requirementMap;
	}

	public void execute(Player p)
	{
		TriggerTask task = new TriggerTask(p, clickEvent);
		task.start();
	}
	
	public boolean meetRequirementWith(Player p)
	{
		return RequirementManager.meetRequirementWith(p, requirementMap).succeed();
	}

	public String getDisplayText()
	{
		return displayText;
	}

	public String getInternalID()
	{
		return internalID;
	}

	public void setDisplayText(String displayText)
	{
		this.displayText = QuestChatManager.translateColor(displayText);
	}

	public String getHoverText()
	{
		return hoverText;
	}

	public void setHoverText(String hoverText)
	{
		this.hoverText = QuestChatManager.translateColor(hoverText);
	}

	public EnumMap<RequirementType, Object> getRequirementMap()
	{
		return requirementMap;
	}

	public void setRequirement(RequirementType type, Object o)
	{
		requirementMap.put(type, o);
	}

	public List<TriggerObject> getClickEvent()
	{
		return clickEvent;
	}

	public void setClickEvent(List<TriggerObject> clickEvent)
	{
		this.clickEvent = clickEvent;
	}
	
}
